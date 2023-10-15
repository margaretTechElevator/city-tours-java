<template>
  <body>
    <div id="back">
      <Navigation />
      <google-map id="map"/>
      <ItineraryList />
      <Route/>
    </div>
  </body>
</template>
  
<script>
  import Route from '../components/Route.vue'
  import GoogleMap from '../components/GoogleMap.vue'
  import Navigation from '../components/Navigation.vue'
  import ItineraryList from '../components/ItineraryList.vue'

  export default {
    name: 'PlanNewTourPage',
    components: { 
      Navigation, 
      GoogleMap, 
      ItineraryList, 
      Route 
    },
    computed: {
      landing() {
        return this.$route.path == "/"
      }
    }
  }
</script>

<style scoped>

</style>