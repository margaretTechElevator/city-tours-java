<template>
    <body>
      <navigation-bar-hamburger/>

      
  <google-map id="mapElement"/>
 <trips-table/>
 <route/>



    </body>
  </template>
  
  <script>
  import route from '../components/route.vue'
  import GoogleMap from '../components/GoogleMap.vue';
import navigationBarHamburger from '../components/navigationBarHamburger.vue'
import TripsTable from '../components/tripsTable.vue';
  export default {
  components: { navigationBarHamburger, GoogleMap, TripsTable, route},
      name: 'PlanNewTourPage',
  
  }
  </script>
  
  <style >
  
  
  </style>