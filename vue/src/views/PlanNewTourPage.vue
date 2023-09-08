<template>
  <body>
    <Navigation />
    <google-map id="mapElement"/>
    <TripsTable />
    <route/>
  </body>
</template>
  
<script>
  import route from '../components/route.vue'
  import GoogleMap from '../components/GoogleMap.vue';
  import Navigation from '../components/Navigation.vue'
  import TripsTable from '../components/TripsTable.vue';

  export default {
    components: { 
      Navigation, 
      GoogleMap, 
      TripsTable, 
      route 
    },
    name: 'PlanNewTourPage',
  }
</script>