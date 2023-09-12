<template>
  <body>
    <Navigation />
    <google-map id="mapElement"/>
    <ItineraryList />
    <Route/>
  </body>
</template>
  
<script>
  import Route from '../components/Route.vue'
  import GoogleMap from '../components/GoogleMap.vue'
  import Navigation from '../components/Navigation.vue'
  import ItineraryList from '../components/ItineraryList.vue'

  export default {
    components: { 
      Navigation, 
      GoogleMap, 
      ItineraryList, 
      Route 
    },
    name: 'PlanNewTourPage',
  }
</script>