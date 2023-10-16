<template>
  <body>
    <div id="searchContainer">
      <div id="searchBackground">
        <div id="searchArea">
          <input
            v-model="currentInput"
            placeholder="starting from?"
            type="input"
            class="input-text"
            id="startingAddress"
          />
          <input
            v-model="radiusInput"
            placeholder="search radius in miles"
            type="input"
            class="input-text"
            id="radius"
          />
        </div>
        <!-- <div id="whatToSearch">things to do</div> -->
        <div id="attractionTypeCheckboxesGroup">
          <div
            v-for="type in attractionTypes"
            :key="type"
            class="attractionTypeCheckboxes"
          >
            <input
              type="checkbox"
              :value="type"
              v-model="selectedTypes"
              :id="`${type}-id`"
              class="checkboxes"
            />
            <label :for="`${type}-id`">{{ type }}</label>
          </div>
        </div>
        <div id="letsGoContainer">
        <button v-on:click="search" id="letsGo">let's go!</button>
      </div>




      </div>
    </div>

    <!--Google Maps will render map here-->
    <div id="map"></div>

    <!-- MOVED TO ROUTE.VUE -->

    <div>
      <p>Current Locations:</p>
      <button v-on:click="generateRoute">Generate Route</button><br /><br />
    </div>

    <div
      id="currentList"
      v-for="(landmark, index) of landmarks"
      v-bind:key="index"
    >
      <input class="current-inputs" v-model="landmark.name" />
      <button v-on:click.prevent="landmark.showDetails = !landmark.showDetails">
        Details
      </button>
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
    <!--Google Maps will render directions here-->
    <div id="panel"></div>
  </body>
</template>
  
<script>
import LandmarkInfo from "./LandmarkInfo.vue";
import { loadedGoogleMapsAPI } from "@/main";

export default {
  name: "Map",
  components: { LandmarkInfo },
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
        mapTypeControl: false,
        fullscreenControl: false,
        zoomControl: false,
      });
      this.map.addEventListener("tilesloaded", () => {});
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
        request, //our request object
        (searchResults, status) => {
          //our function that handles the promise object we are sent back
          if (status === window.google.maps.places.PlacesServiceStatus.OK) {
            this.searchResultLandmarks = [];

            for (let i = 0; i < searchResults.length; i++) {
              const placeId = searchResults[i].place_id;

              //fetch details for each place
              placesService.getDetails(
                { placeId: placeId },
                (landmark, status) => {
                  if (
                    status === window.google.maps.places.PlacesServiceStatus.OK
                  ) {
                    // Create an info window
                    const infoWindow = new window.google.maps.InfoWindow({
                      content: `<h3>${searchResults[i].name}</h3><p>${searchResults[i].vicinity}</p><button id="routeButton${placeId}" value="${placeId}">Add to Route</button>`,
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
                        "domready",
                        () => {
                          const routeButton = document.getElementById(
                            `routeButton${placeId}`
                          );

                          if (routeButton) {
                            routeButton.addEventListener(
                              "click",
                              this.addToItinerary
                            );
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
                      showDetails: false,
                      // marker: marker,
                      // infoWindow: infoWindow
                    };

                    this.searchResultLandmarks.push(newLandmark);
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
      const addedLocationId = event.target.value;

      const addedLocation = this.searchResultLandmarks.filter(
        (location) => location.id === addedLocationId
      );

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
    loadedGoogleMapsAPI.then(() => {
      this.initMap();
    });
  },
};
</script>
  
<style scoped>
  /* move the search area up and down from the logo */
#searchContainer{
  padding-top:70px;
}
#searchBackground {
  background: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
 
  border-radius:  0 0 20px 20px;
}


#searchArea {
padding-top:0px;
position: relative;
height: 100%;
width: 100%;
display: flex;
flex-direction: column;
justify-content: center;
align-items: flex-start;
}
#letsGo {
  font-family: OrelegaOne;
  background-color: transparent;
  border: none;
  color: #adff00;
  border-radius: 20px;
  font-size: 24px;
  margin-right: 16px;
  margin-bottom: 20px;;
  height: 30px;
  box-shadow: 1px 1px 10px rgba(5, 5, 5, 0.186);
  padding-left: 20px;
  padding-right:20px;
  padding-top: 5px;
  padding-bottom:30px;
  
}
#letsGoContainer{
  display: flex;
  justify-content: flex-end;
}
body {
  margin: 0;
}
#map {
  border: 3px red solid;
}

#map {
  width: 100%;
  height: 500px;
  margin-top: 0px;
  position: relative;
  z-index: 0;
}


input {
  width: 300px;
  margin-top: 0px;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  width: 50%;
}

::placeholder {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 166.667% */
  letter-spacing: 1.8px;

  background: transparent;
}

#panel {
}
#radius{
  margin-top: 20px;
}
.input-text {
  color: #ADFF00;
  font-family: Inter;
  font-size: 14px;
  font-style: normal;
  letter-spacing: 1.8px;
  width: 90%;
  background-color: rgb(26, 51, 71);
 
  border-radius: 20px;
  box-shadow: 0px 4px 3px 0px rgba(0, 0, 0, 0.3) inset;
  outline: none;
  border: none;
  height: 30px;
  max-width: 500px;
}

#whatToSearch {
  padding-top: 20px;
  padding-bottom: 15px;
  color: #cfe7ca;
  font-weight: 900;
  font-size: 1.5rem;
  line-height: 0;
  padding-top: 30px;
  text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
  width: 100%;
}
.checkboxes{
  border-radius: 50%;

}
.attractionTypeCheckboxes {
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 166.667% */
  letter-spacing: 1.8px;
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  display: flex;
  align-items: center;
  padding: 3%;
  margin-top: 20px;
  flex-direction: column;
}

#attractionTypeCheckboxesGroup {
  display: flex;
  justify-content: space-between;
  margin-left: auto;
  margin-right: auto;
  max-width: 500px;
}
</style>