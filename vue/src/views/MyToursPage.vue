<template>
  <body>
    <Navigation />
    <div>
      <h4>my tours</h4>
      <button v-on:click="redirectToNewItinerary">+ new itinerary</button>
    </div>
    <ItineraryList />
    <div id="myToursTableBackground"></div>  
  </body>
</template>
  
<script scoped>
  import Navigation from '../components/Navigation.vue'
  import ItineraryList from '../components/ItineraryList.vue'

  export default {
    components: { Navigation, ItineraryList },
    name: 'MyToursPage',
    methods: {
      redirectToNewItinerary() {
        this.$router.push("PlanNewTourPage");
      },
    },
  }
</script>
  
<style scoped>

</style>