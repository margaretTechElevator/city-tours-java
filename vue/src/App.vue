<template>
  <div id="app">
    <topper/>
    <router-view />
    
  </div>
</template>

<script>
import Topper from './components/Topper.vue'
  export default {
  components: { Topper },
    name: 'App',
  }
</script>
<style >
@font-face {
  font-family: 'Orelega One';
  src: url('~@/assets/fonts/OrelegaOne-Regular.ttf') format('truetype');
}
@font-face {
  font-family: 'Inter';
  src: url('~@/assets/fonts/Inter.ttf');
}


body{
  margin:0px;
  background-color:  #182935;
}

</style>