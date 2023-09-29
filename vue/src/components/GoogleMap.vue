<template>
    <div id="grid-container">
      <!--Google Maps will render map here-->
      <div id="map"></div>

      <MapSearchForm id="search-form" @search-submitted="addToList" />

      <!-- MOVED TO ROUTE.VUE -->

        <!-- <div id="cityTourRoute">
          <table>
        <tr>
          <th>city</th>
          <th>date</th>
          <th>starting location</th>
          <th>ending location</th>
          <th>number of stops</th>
       
        </tr>
        <tr>
          <td>first stop</td>
          <td>3miles</td>
        </tr>
      </table>
        <button v-on:click="generateRoute">show route</button><br /><br />
        <div
          id="currentList"
          v-for="(location, index) of locations"
          v-bind:key="index"
        >
          <input class="current-inputs" v-model="locations[index]" />
          <button v-on:click="removeFromList(index)">Remove</button>
        </div>

        <input v-model="currentInput" type="input" placeholder="address" />
        <button v-on:click="addToList">Add a Stop</button>

        <p>Current Locations:</p>
        <button v-on:click="generateRoute">Generate Route</button><br /><br />
      </div> -->
        <div
          id="currentList"
          v-for="(location, index) of locations"
          v-bind:key="index"
        >
          <input class="current-inputs" v-model="locations[index].address" />
          <button v-on:click="removeFromList(index)">Remove</button>
        </div>
      
    <!--Google Maps will render directions here-->
    <div id="panel"></div>
  </div>
</template>
  
<script>
import MapSearchForm from './MapSearchForm.vue'

export default {
  name: "Map",
  components: { MapSearchForm },
  data() {
    return {
      map: null,
      routeService: null,
      routeRendererService: null,
      roundTrip: true,
      mapCenter: { lat: 42.3327, lng: -83.0458 },
      locations: [],
      location: {},
    };
  },

  methods: {
    // This function is called during load, but can also be called to reset the map
    initMap() {
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: this.mapCenter,
        zoom: 14,
        maxZoom: 20,
        minZoom: 3,
        streetViewControl: true,
        mapTypeControl: true,
        fullscreenControl: true,
        zoomControl: true,
      });

      // this feature shows the point of interests in the map
      let showPOIStyle = [
        {
          featureType: "poi",
          elementType: "labels",
          stylers: [{ visibility: "on" }],
        },
      ];
      this.map.setOptions({ styles: showPOIStyle });
    },

    // This function is called to add a new location
    async addToList(submittedForm) {

      //check the location!
      if (submittedForm.search_term.trim().length === 0) {
        window.alert("Location cannot be empty");
        return;
      }

      //check the radius!
      if (submittedForm.search_radius.trim().length === 0) {
        window.alert("Radius cannot be empty");
        return;
      }

   //below code is my code to check if it works
      const geocoder = new window.google.maps.Geocoder();

      const getCoordinates = (address) =>
        new Promise((resolve, reject) => {
          geocoder.geocode({ address: address }, (results, status) => {
            if (status === "OK") {
              resolve({
                lat: results[0].geometry.location.lat(),
                lng: results[0].geometry.location.lng(),
              });
            } else {
              reject(new Error(`could not fetch coordinates: ${status}`));
            }
          });
        });
      try {
        const coordinates = await getCoordinates(submittedForm.search_term);

        //get the location object if user enter location info into the button.
        this.location = {
          address: submittedForm.search_term,
          lat: coordinates.lat,
          lng: coordinates.lng,
        };

        this.locations.push(this.location);

        // Set the map center to the new coordinates and zoom in
        this.map.setCenter(coordinates);
        this.map.setZoom(15);

        // this.currentInput = "";
      } catch (error) {
        window.alert(error.message);
      }

      //initialize places service
      const placesService = new window.google.maps.places.PlacesService(
        this.map
      );

      //define the location (latitude and longitude)
      this.location = new window.google.maps.LatLng(
        this.location.lat,
        this.location.lng
      );

      //set up the places API request parameters

      const request = {
        location: this.location,
        radius: submittedForm.search_radius, //search within 50000 meters
        type: submittedForm.selected_types,
      };

      //make the Places API request
      placesService.nearbySearch(request, (results, status) => {
        if (status === window.google.maps.places.PlacesServiceStatus.OK) {
          for (let i = 0; i < results.length; i++) {
            const placeId = results[i].place_id;

            //fetch details for each place
            placesService.getDetails({ placeId: placeId }, (place, status) => {
              if (status === window.google.maps.places.PlacesServiceStatus.OK) {
  
                const marker = new window.google.maps.Marker({
                  position: results[i].geometry.location,
                  map: this.map,
                  title: results[i].name,
                });

                // Create an info window
                const infoWindow = new window.google.maps.InfoWindow({
                
                  content: `<h3>${results[i].name}</h3><p>${results[i].vicinity}</p>`,
                });

                // Add click event listener to marker to show info window
                marker.addListener("click", () => {
                  infoWindow.open(this.map, marker);
                });
              }
            });
          }
        }
      });
      //till here
    },
    // This function is called to remove a location
    removeFromList(index) {
      if (this.locations.length == 2) {
        window.alert("A start and end location must be present");
        return;
      }

      this.locations.splice(index, 1);
    },

    // This function calls the Google Maps API, renders the route
    // and retrieves the directions
    generateRoute() {
      for (let i = 0; i < this.locations.length; i++) {
        if (this.locations[i].address.trim().length === 0) {
          window.alert("Location cannot be empty");
          return;
        }
      }

      const panel = document.getElementById("panel");
      panel.innerHTML = "";
      this.initMap();

      this.routeService = new window.google.maps.DirectionsService();
      this.routeRendererService = new window.google.maps.DirectionsRenderer();

      this.routeRendererService.setMap(this.map);
      this.routeRendererService.setPanel(panel);

      let myWaypoints = [];

      /*
          The API expects a single waypoint to be an object like this:
          
          {
            location: "123 somewhere St...",
            stopover: true
          }
        */

      for (let i = 1; i < this.locations.length - 1; i++) {
        myWaypoints.push({
          location: this.locations[i],
          stopover: true,
        });
      }

      this.routeService
        .route({
          origin: this.locations[0],
          destination: this.locations[this.locations.length - 1],
          waypoints: myWaypoints,
          travelMode: window.google.maps.TravelMode.DRIVING,
          avoidTolls: true,
          optimizeWaypoints: true,
        })
        .then((result) => {
          this.routeRendererService.setDirections(result);
        })
        .catch((error) => {
          console.log(error + "Could not generate route");
        });
    },
  },

  mounted() {
    this.initMap();
  },
};
</script>
  
  <style>
  input{
    width:300px;
  margin-top:0px;
  margin-left: auto;
  margin-right:auto;
  text-align: center;
  width: 50%;
  box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
  border:rgb(203, 203, 203) 0.5px solid;
  background-color: rgba(158, 158, 158, 0.248);
  }
#grid-container {
  padding-bottom: 20px;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "inputs "
    "map "
    "directions ";
}


#map {
  grid-area: map;
  width: 500px;
  height: 400px;
  padding: 25px;
  margin: 25px;

}


#panel {
  grid-area: directions;
}
button {
  background-color: rgb(236, 191, 93);
  border: none;
  text-align: center;
  padding-top: 8px;
  padding-bottom: 8px;
  color: #6b1717;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  font-size: 1rem;
  line-height: 1;
  box-shadow: 1px 1px 10px rgba(130, 114, 110, 0.186);

}
::placeholder {
  color: #e0a788e0;
  font-weight: 900;
  letter-spacing: 0.15rem;
  font-size: 0.75rem;
  background: transparent;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}

  #search-form {
    grid-area: inputs;
  }


  

</style>