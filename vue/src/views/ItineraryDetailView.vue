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
      }
    },
    methods: {
      getItineraryById(itineraryId) {
        ItineraryService.getItineraryById(itineraryId).then((response) => {
          this.itinerary = response;
        })
        .catch((error) => {
          this.failureMessage = `Our apologies! ${error.response}`;
        })
      },
    },
    mounted() {
      this.getItineraryById(this.$route.params.itineraryId);
    },
  }
</script>

<style>

</style>