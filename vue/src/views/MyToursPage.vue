<template>
  <body>
    <Navigation />
    <h4>my tours</h4>
    <ItineraryList />
    <div id="myToursTableBackground"></div>  
  </body>
</template>
  
<script scoped>
  import Navigation from '../components/Navigation.vue'
  import ItineraryList from '../components/ItineraryList.vue'

  export default {
    components: { Navigation, ItineraryList },
    name: 'MyToursPage'
  }
</script>
  
<style>
  body {
    background: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png");
  }

  #myToursTableBackground {
    background-color: #fffefe;
    width: auto;
    height:900px;
  }

  h4 {
    margin:auto;
    width: 80%;
    text-align: center;
    text-shadow: 1px 1px 15px rgba(215, 255, 53, 0.277);

    font-size: 2.5rem;
    font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-weight: 900;
    color:rgb(1, 37, 105);
    padding-top:20px;
    padding-bottom: 10px;  
  }
</style>