<template>
  <div>
  

      <div>Landing Page</div>
      <SearchBarFilterGo />
      <google-map />


  </div>
</template>
  
<script>
// import GoogleMap from "../components/GoogleMap.vue";
import SearchBarFilterGo from "../components/searchBarFilterGo.vue";


export default {
  name: "Landing",

  components: {SearchBarFilterGo },
  // components: { Navigation, SearchBarFilterGo, GoogleMap },
};
</script>
  
<style scoped>


</style>