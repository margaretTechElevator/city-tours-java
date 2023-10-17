<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
  export default {
    name: 'App',
  }
</script>
<style scoped>
body{

  margin:0;
  padding:0;
}
@font-face {
  font-family: "OrelegaOne";
  src: url("assets/fonts/OrelegaOne-Regular.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "Inter";
  src: url("assets/fonts/Inter-Regular.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "Inter";
  src: url("assets/fonts/Inter-Light.ttf") format("truetype");
  font-weight: normal;
  font-style: normal;
}

</style>
