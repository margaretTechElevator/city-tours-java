<template>
  <div>
    <div id="grid-container">
      <!--Google Maps will render map here-->
      <div id="map"></div>

      <div id="searchArea" class="grid">
        <input
          v-model="currentInput"
          placeholder="starting address"
          type="input"
          class="inputStartingMiles"
          id="startingAddress"
        />
        <input
          v-model="radiusInput"
          placeholder="search radius in miles"
          type="input"
          class="inputStartingMiles"
          id="milesFrom"
        />
        <p></p>
        <span id="whatToSearch">things to do</span>
      
        <div v-for="type in attractionTypes" :key="type">
          <input type="checkbox" :value="type" v-model="selectedTypes"/>
          {{ type }}
        </div>

        <button v-on:click="addToList" id="letsGo">let's go!</button>
      </div>

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
          <input class="current-inputs" v-model="locations[index]" />
          <button v-on:click="removeFromList(index)">Remove</button>
        </div>
      
    </div>

    <!--Google Maps will render directions here-->
    <div id="panel"></div>
  </div>
</template>
  
  <script>
export default {
  name: "Map",
  data() {
    return {
      map: null,
      routeService: null,
      routeRendererService: null,
      currentInput: "",
      radiusInput: "",
      typeInput: "",
      userDayInput: "",
      attractionTypes: ["museum", "cafe", "restaurant", "park"],
      selectedTypes: [],
      roundTrip: true,
      mapCenter: { lat: 42.3327, lng: -83.0458 },
      locations: [],
      location: {},
      //markers when user want to see the landmarks on the map
      // markers: [],
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
    async addToList() {
      //check the location!
      if (this.currentInput.trim().length === 0) {
        window.alert("Location cannot be empty");
        return;
      }

      //check the radius!
      if (this.radiusInput.trim().length === 0) {
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
        const coordinates = await getCoordinates(this.currentInput);

        //get the location object if user enter location info tinto the button.
        this.location = {
          address: this.currentInput,
          lat: coordinates.lat,
          lng: coordinates.lng,
        };

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
        radius: this.radiusInput, //search within 50000 meters
        type: this.selectedTypes,
      };
      console.log("current selected types:", this.selectedTypes);
      console.log(request);

      //to store the markers
      // const markers = [];

      //make the Places API request
      placesService.nearbySearch(request, (results, status) => {
        if (status === window.google.maps.places.PlacesServiceStatus.OK) {
          for (let i = 0; i < results.length; i++) {
            console.log(`Landmark: ${results[i].name}`);
            console.log(`'Place Id: ${results[i].place_id}'`);
            const placeId = results[i].place_id;

            //fetch details for each place
            placesService.getDetails({ placeId: placeId }, (place, status) => {
              console.log(place.current_opening_hours);
              if (status === window.google.maps.places.PlacesServiceStatus.OK) {
                //check if this place is open on the user-specified day
                // if(place.opening_hours && place.opening_hours.weekday_text){
                //   const daysOpen = place.opening_hours.weekday_text;
                //   const isOpenUserDay = daysOpen.some(dayInfo => dayInfo.startsWith(this.userDayInput));

                //   if(isOpenUserDay) {
                // Create a marker
                // const marker = new window.google.maps.Marker({
                //   position: place.geometry.location,
                //   map: this.map,
                //   title: place.name
                // });
                const marker = new window.google.maps.Marker({
                  position: results[i].geometry.location,
                  map: this.map,
                  title: results[i].name,
                });

                // Create an info window
                const infoWindow = new window.google.maps.InfoWindow({
                  //   content: `<h3>${place.name}</h3><p>${place.formatted_address}</p>`
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
      (this.currentInput = this.location.address),
        this.locations.push(this.currentInput);
      this.currentInput = "";
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
        if (this.locations[i].trim().length === 0) {
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

#searchArea {
  /* border: rgb(28, 153, 11) 3px solid; */
  margin: auto;
  width: 50%;
  grid-area: inputs;
  background-color: rgb(255, 255, 255);
  padding-bottom: 40px;;
  
}
.inputStartingMiles{
  width:300px;
  margin-top:10px;
  margin-left: auto;
  margin-right:auto;
  text-align: center;
  width: 100%;
  box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
  border:rgb(203, 203, 203) 0.5px solid;
  background-color: rgba(158, 158, 158, 0.248);

}

/* Please help get these in a horizontal row */


.grid{
  display:grid;
  grid-template-columns: 500px 100px 100px 100px ;
  grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas: 
  "startingAddress startingAddress startingAddress startingAddress",
  "milesFrom milesFrom milesFrom milesFrom",
  " . whatToSearch whatToSearch .",
  "radio radio radio radio",
  ". letsGo letsGo .";
 
}
#milesFrom{
  grid-area: milesFrom;
}
#startingAddress{
  grid-area: startingAddress;
} 
#whatToSearch{
  grid-area: whatToSearch;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 15px;
  color: #6b1717;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  font-size: 1.5rem;
  line-height: 0;
  text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
}
#letsGo{
  grid-area: letsGo;

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



  

</style>