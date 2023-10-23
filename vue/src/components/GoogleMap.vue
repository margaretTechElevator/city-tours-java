<template>
  <body>
    <div id="mapAreaWithDirections">
      <div id="searchContainer">
        <div id="searchBackground">
          <div id="startingFromArea">
            <input
              v-model="currentInput"
              placeholder="starting from?"
              type="input"
              class="input-text"
              id="startingAddress"
              @click="toggleMenu"
            />
            <!-- <input
            v-model="radiusInput"
            placeholder="search radius in miles"
            type="input"
            class="input-text"
            id="radius"
          /> -->
          </div>
        </div>
        <div id="toggleSearchMenu" v-show="menuVisible">
          <div id="radiusArea">
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

      <div id="routeButton">
        <p>Current Locations:</p>
        <button v-on:click="generateRoute">Generate Route</button><br /><br />
      </div>

      <div
        id="currentList"
        v-for="(landmark, index) of landmarks"
        v-bind:key="index"
        v-on:click.prevent="landmark.showDetails = !landmark.showDetails"
      >
        <!-- trip title -->
        <div id="tripTitle" @click="toggleTripDetails">
          <input
            placeholder="my new trip"
            class="input-text"
            @click="showTripDetails"
          />
        </div>

        <div id="locationCard" v-show="showLocationDetails">
         

              <!-- <button id="removeButton" v-on:click="removeFromItinerary(index)">
                -
              </button> -->
           
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
      menuVisible: false,
      showLocationDetails: true,
      showTripDetails: true,
    };
  },

  methods: {
    //toggle up trip details when you click on the trip name input
    toggleTripDetails() {
      this.showTripDetails = !this.showTripDetails;
      const tripDetails = document.getElementById("currentList");
      if (this.showTripDetails) {
        tripDetails.style.top = "90%";
      } else {
        tripDetails.style.top = "30%";
      }
    },

    // toggle location details
    // toggleLocationDetails() {
    //   this.showLocationDetails = !this.showLocationDetails;
    //   const locationCard = document.getElementById("locationCard");
    //   if (this.showLocationDetails) {
    //     locationCard.style.display = "block";
    //   } else {
    //     locationCard.style.display = "none";
    //   }
    // },

    // toggle search area

    toggleMenu() {
      // Toggle the menu visibility
      this.menuVisible = !this.menuVisible;
      // toggle the size of the dropdown
      const startingFromArea = document.getElementById("startingFromArea");
      // Check the value of menuVisible and modify the border radius accordingly
      if (this.menuVisible) {
        startingFromArea.style.borderRadius = "0";
      } else {
        // Restore the original border radius
        startingFromArea.style.borderRadius = " 0 0 20px 20px";
      }
    },

    initMap() {
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: this.mapCenter,
        zoom: 13,
        // maxZoom: 24,
        // minZoom: 8,
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
        this.map.setZoom(19);

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
                    //styling inline MOVE LATER
                    const infoWindow = new window.google.maps.InfoWindow({
                      content: `<div class="locationPopContainer">


                        <div class="locationTitle" 
                      style="
                      font-family: Inter; 
                      font-size: 14px;
                      letter-spacing: 1.2px; 
                      color: #194F77;
                      font-weight: 900;">

                      ${searchResults[i].name}</div>
                      <p 
                      style="
                      font-family:Inter; 
                      font-size:10px; 
                      opacity: 70%;
                      letter-spacing: 1.2px; ">
                      ${searchResults[i].vicinity}</p>

                      <button class="addButton"id="routeButton${placeId}" value="${placeId}"
                      style="
                      background-color: #194F77;
                      border-radius: 50%;
                      width: 20px;
                      height: 20px;
                      font-color: #FFFFFF;
                      text-align: center;
                      ">+</button>
                      </div>`
                      ,
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
      this.toggleMenu();
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
#routeButton {
  display: none;
}

/* move the search area up and down from the logo */
#searchContainer {
  padding-top: 20px;
}
#toggleSearchMenu {
  background: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  border-radius: 0 0 20px 20px;
  box-shadow: -1px 5px 5px rgba(5, 5, 0, 0.4);
  z-index: 1;
  position: relative;
}
#searchBackground {
  border-radius: 0 0 20px 20px;
  z-index: 2;
  position: relative;
}

#startingFromArea {
  padding-bottom: 26px;
  height: 100%;
  width: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  background-color: #182935;
  border-radius: 0 0 20px 20px;
}
#startingAddress {
  max-width: 600px;
  width: 90%;
}
#radiusArea {
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
  margin-bottom: 20px;
  height: 30px;

  padding-left: 20px;
  padding-right: 20px;
  padding-top: 6px;
  padding-bottom: 30px;
}
#letsGoContainer {
  display: flex;
  justify-content: flex-end;
}
body {
  margin: 0;
  z-index: -100;
}
/* locations details
top: 180px; */

#currentList {
  width: 100%;
  position: absolute;
  top: 90%;
  z-index: 50;
  height: 100%;
  background: linear-gradient(
    0deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  border-radius: 20px 20px 0 0;
  box-shadow: 5px -1px 5px rgba(5, 5, 0, 0.4);
}
#map {
  top: 120px;
  width: 100%;
  height: 100%;
  margin-top: -20px;
  position: absolute;
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

.input-text {
  color: #adff00;
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  letter-spacing: 1.8px;
  width: 90%;
  max-width: 600px;
  background-color: rgb(26, 51, 71);

  border-radius: 20px;
  box-shadow: 0px 4px 3px 0px rgba(0, 0, 0, 0.3) inset;
  outline: none;
  border: none;
  height: 30px;
}

.checkboxes {
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
  padding-right: 10px;
  margin-top: 20px;
  flex-direction: column;
}

#attractionTypeCheckboxesGroup {
  display: flex;
  justify-content: space-around;
  margin-left: auto;
  margin-right: auto;
  max-width: 500px;
  padding-left: 16px;
  padding-right: 16px;
}
#attractionTypeCheckboxesGroup input {
  /* opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0; */
}
textarea {
  resize: none;
}

/*bottom route area */
#locationCard {
  width: 100%;
  min-width: 300px;
  max-width: 500px;
  height: 160px;
  z-index: 200;
  position: relative;
  margin: auto;
  top: 40px;
  justify-content: center;
  /* display: flex; */
}


#tripTitle {
  /* font-family: OrelegaOne;
  font-size: 24px;
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5)); */
  z-index: 300;
  padding-top: 20px;
  position: relative;
  width: 100%;
  text-align: center;
  height: 30px;
  justify-content: center;
  border-radius: 0 0 20px 20px;
}
#landmarkPhoto {
  margin: auto;
  position: absolute;
  border-radius: 50%;
  height: 70px;
  width: 70px;
  box-shadow: 5px -1px 5px rgba(5, 5, 0, 0.4);
  cursor: pointer;
  top: 80px;
  left: 50%;
  transform: translateX(-50%);
}

#landmarkName {
  font-family: OrelegaOne;
  font-size: 24px;
  color: rgba(207, 231, 202);
  background-color: transparent;
  text-decoration: none;
  /* resize: none; */
  text-transform: lowercase;
  text-align: center;
  border: none;
  width: 100%;
}

#detailsButton {
  text-align: center;
  border: none;
}

#removeButton {
  width: 30px;
  height: 30px;
  min-width: 30px;
  min-height: 30px;
  position: absolute;
  text-align: center;
  border-radius: 50%;
  background-color: rgba(207, 231, 202);
  font-family: Inter;
  font-weight: 900;
  font-size: 48;
  border: none;
  bottom: 0px;
  left: 26px
}
#removeButton:hover {
  background-color: #adff00;
}
.currentInput {
  text-decoration: none;
}

/* location pop up styling */
#locationTitle{
  color: red;
  border: pink solid 3px;
}
.addButton{
  border-radius: 50%;
}
</style>