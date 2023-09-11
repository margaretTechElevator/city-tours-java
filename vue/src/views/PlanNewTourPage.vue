<template>
  <body>
    <Navigation />
    <google-map id="mapElement"/>
    <TripsTable />
    <Route/>
  </body>
</template>
  
<script>
  import Route from '../components/Route.vue'
  import GoogleMap from '../components/GoogleMap.vue';
  import Navigation from '../components/Navigation.vue'
  import TripsTable from '../components/TripsTable.vue';

  export default {
    components: { 
      Navigation, 
      GoogleMap, 
      TripsTable, 
      Route 
    },
    name: 'PlanNewTourPage',
  }
</script>