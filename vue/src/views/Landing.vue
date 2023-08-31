<template>
    <body>
      <navigation-bar-hamburger/>
      <h1 id="title">Landing Page</h1>
      <google-map/>
     
    </body>
  </template>
  
  <script>
import GoogleMap from '../components/GoogleMap.vue';

import NavigationBarHamburger from '../components/navigationBarHamburger.vue';
  
  export default {
      name:"landing",
      components: {  GoogleMap, NavigationBarHamburger},
  
  }
  </script>
  
  <style>
  title{
      font-size: 10px;
  }
  
  </style>