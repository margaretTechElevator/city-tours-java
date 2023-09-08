<template>
    <body>
      <div id="back">
      <Navigation />
      <div id="tagline">  <div>your city,</div>  <div>your adventure,</div><div>perfectly planned.</div>
    </div>
        <google-map id="mapElement"/>
      </div>
    </body>
  </template>
  
  <script>
import GoogleMap from '../components/GoogleMap.vue';
  import Navigation from '../components/Navigation.vue';
  
  export default {
    name: "Landing",
    components: { GoogleMap, Navigation },
  
  }
  </script>
  
  <style >
  #back { 
    background-image: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png");


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
      padding-top:0px;
      padding-top:20px;
  }
 #mapElement{
    margin:auto;
    text-align: center;
    margin-top: 0px;
    padding-top: 20px;
    box-shadow: 1px 1px 10px rgba(130, 114, 110, 0.17);
    background-color:white;
  }

/* map styling */
  #grid-container {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "inputs "
    "map "
    "directions ";
}
#map {
  grid-area: map;
  width: 500px;
  height: 400px;
  padding: 25px;
  margin: auto;
  margin-top: 20px;
  text-align: center;

}
#panel {
  grid-area: directions;
}
#searchArea {
  /* border: rgb(28, 153, 11) 3px solid; */
  margin: auto;
  width: 50%;
  grid-area: inputs;
  height:110px;
  
}
.inputStartingMiles{
  width:300px;
  margin-top:10px;
  margin-left: auto;
  margin-right:auto;
  text-align: center;
  width: 100%;
  box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
  border:rgb(203, 203, 203) 0.5px solid;
  background-color: rgba(158, 158, 158, 0.248);

}
#attractionTypesRadio{
  /* border: black 3px solid; */
  grid-area: radio;

  
  

}
.grid{
  display:grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas: 
  "startingAddress startingAddress startingAddress startingAddress"
  "milesFrom milesFrom milesFrom milesFrom"
  " . whatToSearch whatToSearch ."
  "radio radio radio radio"
  ". letsGo letsGo .";
 
}
#milesFrom{
  grid-area: milesFrom;
}
#startingAddress{
  grid-area: startingAddress;
}
#whatToSearch{
  grid-area: whatToSearch;
  padding-bottom: 15px;
  color: #6b1717;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  font-size: 1.5rem;
  line-height: 0;
  text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
}
#letsGo{
  grid-area: letsGo;

}
button {
  background-color: rgb(236, 191, 93);
  border: none;
  text-align: center;
  padding-top: 8px;
  padding-bottom: 8px;
  color: #6b1717;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  font-size: 1rem;
  line-height: 1;
  box-shadow: 1px 1px 10px rgba(130, 114, 110, 0.186);

}
::placeholder {
  color: #e0a788e0;
  font-weight: 900;
  letter-spacing: 0.15rem;
  font-size: 0.75rem;
  background: transparent;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
}


  
  </style>