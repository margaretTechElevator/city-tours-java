<template>
  <body>
    <div id="back">
     
      <Navigation />
      <div class="mapContainer">
      <google-map/>
    </div>
    
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

/* #mapBehindNav{
  position: relative;
  z-index: 0;
} */
Navigation{
  position: relative;
  z-index: 2;
}
google-map{
  position: relative;
  z-index: 0;
}
#mapContainer{
  margin-top: 110px;
}
</style>