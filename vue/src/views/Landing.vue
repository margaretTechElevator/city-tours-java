<template>
    <body>
      <navigation-bar-hamburger/>
      <div id="tagline">  <div>your city,</div>  <div>your adventure,</div><div>perfectly planned.</div>
    </div>
        <google-map/>
     
    </body>
  </template>
  
  <script>
import GoogleMap from '../components/GoogleMap.vue';
import NavigationBarHamburger from '../components/navigationBarHamburger.vue';
  
  export default {
      name:"landing",
      components: {GoogleMap, NavigationBarHamburger},
  
  }
  </script>
  
  <style>
  body {
  /* background-image: url("https://i.ibb.co/svhYTX5/city.png"); */
}
  #tagline{
    margin:auto;
    width: 80%;
    text-align: center;
    text-shadow: 1px 1px 15px rgba(215, 255, 53, 0.277);

    /* width:175px; */
      font-size: 2.5rem;
      font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
      font-weight: 900;
      color:rgb(1, 37, 105);
  }
  
  </style>