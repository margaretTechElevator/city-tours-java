<template>
  <body>
    <Navigation />
    <h1>Tours Page</h1>
    <div v-if="!failureMessage">
      <p>{{ this.itinerary }}</p>
      <div
        v-for="(landmark, index) of itinerary.landmarks"
        v-bind:key="index" 
      >
        <LandmarkInfo 
          v-bind:landmarkName="landmark.name"
          v-bind:address="landmark.address"
          v-bind:photos="landmark.photos"
          v-bind:phoneNumber="landmark.phoneNumber"
          v-bind:website="landmark.website"
        />
      </div>
      <button v-on:click="viewEditItinerary">Edit Itinerary</button>
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
    },
    mounted() {
      this.getItineraryById(this.$route.params.itineraryId);
    },
  }
</script>

<style>

</style>