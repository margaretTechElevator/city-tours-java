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
        <input v-model="radiusInput" 
          placeholder="search radius in miles" 
          type="input" 
          class="inputStartingMiles"
          id="milesFrom" 
        />
        <p></p>
        <span id="whatToSearch">things to do</span>

        <div v-for="type in attractionTypes" :key="type">
          <input type="checkbox" :value="type" v-model="selectedTypes" />
          {{ type }}
        </div>

        <button v-on:click="search" id="letsGo">let's go!</button>
      </div>

      <!-- MOVED TO ROUTE.VUE -->

      <div id="cityTourRoute">
        <p>Current Locations:</p>
        <button v-on:click="generateRoute">Generate Route</button><br /><br />
      </div>

      <div id="currentList" 
        v-for="(landmark, index) of landmarks"
        v-bind:key="index"
      >
        <input 
          class="current-inputs" 
          v-model="landmark.name"
        />
        <button v-on:click.prevent="landmark.showDetails=!landmark.showDetails">Details</button>
        <button v-on:click="removeFromItinerary(index)">Remove</button>
        <LandmarkInfo 
          v-bind:name="landmark.name"
          v-bind:address="landmark.address"
          v-bind:photos="landmark.photos"
          v-bind:phoneNumber="landmark.phoneNumber"
          v-bind:website="landmark.website"
          v-show="landmark.showDetails"
        />
      </div>
    </div>

    <!--Google Maps will render directions here-->
    <div id="panel"></div>
  </div>
</template>
  
<script>
import LandmarkInfo from './LandmarkInfo.vue'
export default {
  name: "Map",
  components: { LandmarkInfo },
  data() {
    return {
      map: null,
      routeService: null,
      routeRendererService: null,
      currentInput: "beacon park",
      radiusInput: "200",
      typeInput: "",
      userDayInput: "",
      attractionTypes: ["museum", "cafe", "restaurant", "park"],
      selectedTypes: [],
      roundTrip: true,
      mapCenter: { lat: 42.3327, lng: -83.0458 },
      searchResultLandmarks: [],
      landmarks: [],
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
    async search() {
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

        //get the location object if user enter location info into the button.
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
      this.mapslocation = new window.google.maps.LatLng(
        this.location.lat,
        this.location.lng
      );

      //set up the places API request parameters
      const request = {
        location: this.mapslocation,
        radius: this.radiusInput, //search within 50000 meters
        type: this.selectedTypes,
      };


      //make the Places API request
      placesService.nearbySearch(
        request,                      //our request object
        (searchResults, status) => {        //our function that handles the promise object we are sent back
          if (status === window.google.maps.places.PlacesServiceStatus.OK) {
            this.searchResultLandmarks = []


            for (let i = 0; i < searchResults.length; i++) {

              const placeId = searchResults[i].place_id;

              //fetch details for each place
              placesService.getDetails(
                { placeId: placeId },
                (landmark, status) => {
                  if (status === window.google.maps.places.PlacesServiceStatus.OK) {

                    // Create an info window
                    const infoWindow = new window.google.maps.InfoWindow({
                      content:
                        `<h3>${searchResults[i].name}</h3><p>${searchResults[i].vicinity}</p><button id="routeButton${placeId}" value="${placeId}">Add to Route</button>`,
                    });

                    // Create map marker
                    const marker = new window.google.maps.Marker({
                      position: searchResults[i].geometry.location,
                      map: this.map,
                      title: searchResults[i].name,
                    });

                    // Add click event listener to marker to show info window
                    marker.addListener("click", () => {
                      //open info window when marker is selected
                      infoWindow.open(this.map, marker);

                      //add listener to "add to route" button
                      window.google.maps.event.addListener(
                        infoWindow,
                        'domready',
                        () => {
                          const routeButton = document.getElementById(`routeButton${placeId}`)

                          if (routeButton) {
                            routeButton.addEventListener('click', this.addToItinerary)
                          }
                        }
                      );

                      //CREATE SIDEBAR
                    });

                    const newLandmark = {
                      id: placeId,
                      name: landmark.name,
                      address: landmark.formatted_address,
                      reviews: landmark.reviews,
                      photos: landmark.photos,
                      rating: landmark.rating,
                      phoneNumber: landmark.formatted_phone_number,
                      website: landmark.website,
                      showDetails: false
                      // marker: marker,
                      // infoWindow: infoWindow
                    }
                    
                    this.searchResultLandmarks.push(newLandmark)
                  }
                }
              );
            }
          }
        }
      );
      //till here
    },
    addToItinerary(event) {
      const addedLocationId = event.target.value

      const addedLocation = this.searchResultLandmarks.filter(location => location.id === addedLocationId)
      
      if (addedLocation.length === 1) {
        this.landmarks.push(addedLocation[0]);
      }

    },
    // This function is called to remove a location
    removeFromItinerary(index) {
      if (this.landmarks.length == 2) {
        window.alert("A start and end location must be present");
        return;
      }

      this.landmarks.splice(index, 1);
    },

    // This function calls the Google Maps API, renders the route
    // and retrieves the directions
    generateRoute() {
      for (let i = 0; i < this.landmarks.length; i++) {
        if (this.landmarks[i].address.trim().length === 0) {
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

      for (let i = 1; i < this.landmarks.length - 1; i++) {
        myWaypoints.push({
          location: this.landmarks[i].address,
          stopover: true,
        });
      }

      this.routeService
        .route({
          origin: this.landmarks[0].address,
          destination: this.landmarks[this.landmarks.length - 1].address,
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
input {
  width: 300px;
  margin-top: 0px;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  width: 50%;
  box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
  border: rgb(203, 203, 203) 0.5px solid;
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
  padding-bottom: 40px;
  ;

}

.inputStartingMiles {
  width: 300px;
  margin-top: 10px;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  width: 100%;
  box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
  border: rgb(203, 203, 203) 0.5px solid;
  background-color: rgba(158, 158, 158, 0.248);

}

Please help get these in a horizontal row
.grid {
  display: grid;
  grid-template-columns: 500px 100px 100px 100px;
  grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "startingAddress startingAddress startingAddress startingAddress",
    "milesFrom milesFrom milesFrom milesFrom",
    " . whatToSearch whatToSearch .",
    "radio radio radio radio",
    ". letsGo letsGo .";

}

#milesFrom {
  grid-area: milesFrom;
}

#startingAddress {
  grid-area: startingAddress;
}

#whatToSearch {
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

#letsGo {
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