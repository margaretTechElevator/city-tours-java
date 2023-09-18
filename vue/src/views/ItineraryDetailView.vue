<template>
  <body>
    <Navigation />
    <h1>Tours Page</h1>
    <div v-if="!failureMessage">
      <div id="itinerary">
        <div id="itinerary-details">
          <h1>{{ this.itinerary.startLocation }}</h1>
          <p>{{ this.itinerary.date }}</p>
          <p>{{ this.itinerary.endLocation }}</p>
          <button v-on:click="viewEditItinerary">Edit Itinerary</button>
          <button v-on:click="deleteItinerary">Remove Itinerary</button>
        </div>
        <div class="list">
          <div
            v-for="(landmark, index) of this.itinerary.landmarks"
            v-bind:key="index"
            class="card" 
          >
            <LandmarkInfo 
              v-bind:name="landmark.name"
              v-bind:address="landmark.address"
              v-bind:photos="landmark.photos"
              v-bind:phoneNumber="landmark.phoneNumber"
              v-bind:website="landmark.website"
            />
          </div>
        </div>
      </div>
    </div>
    <div v-else>{{ this.failureMessage }}</div>
  </body>
</template>

<script>
  import ItineraryService from '../services/ItineraryService'
  import Navigation from '../components/Navigation.vue'
  import LandmarkInfo from '../components/LandmarkInfo.vue'

  export default {
    name: "ItineraryDetailView",
    components: { 
      Navigation, 
      LandmarkInfo,
    },
    data() {
      return {
        itinerary: {},
        failureMessage: "",
        // landmark: {
        //   name: "Landmark 1 hardcoded",
        //   placeId: "someplaceid",
        //   address: "422 Bullvead",
        //   reviews: {},
        //   photos: [{}],
        //   phoneNumber: "93939445451",
        //   ratings: {},
        //   website: "landmarkbasby.com"
        // }
      }
    },
    methods: {
      getItineraryById(itineraryId) {
        ItineraryService.getItineraryById(itineraryId)
        .then((response) => {
          this.itinerary = response;
        })
        .catch((error) => {
          this.failureMessage = `Our apologies! ${error.response}`;
        })
      },
      viewEditItinerary() {
        this.$router.push(`/planNewTourPage/${this.$route.params.itineraryId}`);
      },
      deleteItinerary() {
        ItineraryService.updateItinerary(this.$route.params.itineraryId)
        .then(() => {
          console.log("This calls on the backend to remove an itinerary.");
          // I (Sam) don't see a controller method for this yet, but I consider it out of scope for my present ticket
        })
        .catch(() => {
          this.failureMessage = `Our apologies! We were unable to delete this itinerary.`;
        })
      },
    },
    mounted() {
      this.getItineraryById(this.$route.params.itineraryId);
    },
  }
</script>

<style>
  #itinerary {
    /* display: grid; */
  }
  #itinerary-details {
    width: 50%;
    float: right;
  }
  .list {
    border: 1px red solid;
    display: grid;
    width: 30%;
    float: left;
  }
  .card {
    border: 1px blue dotted;
    margin: 2em;
  }
</style>