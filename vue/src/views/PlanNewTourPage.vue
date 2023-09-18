<template>
  <body>
    <Navigation />
    <google-map id="mapElement"/>
    <ItineraryList />
    <Route/>
    <button @click="saveItinerary">Save itinerary</button>
  </body>
</template>
  
<script>
  import Route from '../components/Route.vue'
  import GoogleMap from '../components/GoogleMap.vue'
  import Navigation from '../components/Navigation.vue'
  import ItineraryList from '../components/ItineraryList.vue'
  import ItineraryService from '../services/ItineraryService'

  export default {
    components: { 
      Navigation, 
      GoogleMap, 
      ItineraryList, 
      Route 
    },
    name: 'PlanNewTourPage',
    methods: {
      saveItinerary() {
        if (this.$route.params.itineraryId) {
          console.log("Successfully called on component update itinerary method");
          // might be nice to bounce this before we get to the controller method (which will at least check for user status) for more thorough security, since this permits any random number to appear as an itineraryId
          this.updateItinerary();
        } else {
          console.log("Successfully called on component add itinerary method");
          this.addItinerary();
        }
      },
      updateItinerary() {
        ItineraryService.updateItinerary(this.$route.params.itineraryId)
        .then(() => {
          window.alert("Itinerary was updated if you were authenticated");
        })
        .catch(() => {
          this.failureMessage = `Our apologies! Please try again.`;
        })
      },
      addItinerary() {
        console.log("Here is where we will check for authentication and begin to worry about an itinerary as a form");
      }
    }
  }
</script>