<template>
  <body>
    <div id="googleMaps">
      <Map></Map>
    </div>

    <div id="navigation">
      <Topper/>
      <Navigation />
    </div>
    
    <div id="search">
      <SearchBarFilterGo/> 
    </div>
  </body>
</template>
  
<script>
import Map from "../components/GoogleMap.vue";
import Navigation from "../components/Navigation.vue";
import SearchBarFilterGo from '../components/searchBarFilterGo.vue';
import Topper from "../components/Topper.vue";

export default {
  name: "Landing",
  components: { Navigation, Map, Topper, SearchBarFilterGo },
};
</script>
  
<style >
#googleMaps {
  background-color: bisque;
  height: 100%;
  width: 100%;
  /* was absolute and it was working but the bottom nav wasnt showing up */
  position: relative;
  top: 0;
  left: 0;
  z-index: 0;
}

#navigation {
  position: relative;
  z-index: 1;
}
#searchArea{
  /* non-scoped way to access and hide the search area 
  and filters in the GoogleMap component */
  display: none;
}
body{
  margin: 0px;
}

SearchBarFilterGo{
  z-index:4;
  width: 400px;
  height:900px;
  bottom: 0px;

}

</style>