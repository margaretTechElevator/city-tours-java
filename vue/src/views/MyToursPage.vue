<template>
  <div id="myToursPage">
    <Navigation />
    <div id="myToursContainer">
      <!-- individual tour card -->
      <div id="tourCard">
        <div id="tripTitle">New Orleans for Mardi Gras with the girls</div>
        <div id="timeAndRoute">
          <remove-icon id="removeIcon" class="tourIcon"></remove-icon>
          <div id="travelTime">1 hr 35 min</div>
          <route-icon id="routeIcon"></route-icon>
        
        </div>

        <!-- v-for each location in the itinerary, 
        a photo that gets slightly larger when hover or click
        on click, the location information and icons pop up 
        under the location photos -->
        <div id="locationsContainer">
          
          <div id="locationPhotosContainer">
            <span> <img class="locationPhoto" src="../assets/samplePhoto.jpg" /></span>
            <span> <img class="locationPhoto" src="../assets/samplePhoto.jpg" /></span>
            <span> <img class="locationPhoto" src="../assets/samplePhoto.jpg" /></span>
            <span> <img class="locationPhoto" src="../assets/samplePhoto.jpg" /></span>
          </div>
          <hr>
          <div id="locationTitle">this location title</div>
          <div id="locationDetailsPopUp">
            

            <div id="locationDetails">some location details to popup some location details to popup some location details to popup some location details to popup some location details to popup some location details to popup some location details to popup some location details to popup</div>

            <div class="tourIcons">
              <location-icon class="tourIcon"></location-icon>
              <call-icon class="tourIcon"></call-icon>
              <website-icon class="tourIcon"></website-icon>
              <star-icon class="tourIcon"></star-icon>
            </div>
            
          </div>
        </div>
      
      </div>
    </div>
    <ItineraryList />
    <div id="myToursTableBackground"></div>
  </div>
</template>
  
<script scoped>
import Navigation from "../components/Navigation.vue";
import ItineraryList from "../components/ItineraryList.vue";
import routeIcon from "../assets/routeIcon.vue";
import RemoveIcon from '../assets/removeIcon.vue';
import CallIcon from '../assets/callIcon.vue';
import WebsiteIcon from '../assets/websiteIcon.vue';
import StarIcon from '../assets/starIcon.vue';
import locationIcon from '../assets/locationIcon.vue'

export default {
  components: { Navigation, ItineraryList, routeIcon, RemoveIcon, CallIcon, WebsiteIcon, StarIcon, locationIcon },
  // ,
  //     RouteIconname: 'MyToursPage',
  // methods: {
  //   redirectToNewItinerary() {
  //     this.$router.push("PlanNewTourPage");
  //   },
  // },
};
</script>
  
<style scoped>
#myToursPage {
  background: linear-gradient(
    0deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  height: 100vh;
}
#myToursContainer {
  margin-top: 30px;
  display: flex;
  justify-content: center;
}
#tourCard {
  border-radius: 20px;
  background-color: #182935;
  width: 90%;
  height: 300px;
  min-width: 300px;
  max-width: 500px;
}
#tripTitle {
  text-align: center;
  color: rgba(207, 231, 202);
  font-family: Inter;
  font-size: 14px;
  letter-spacing: 1.2px;
  background-color: transparent;
  text-decoration: none;
  text-transform: lowercase;
  border: none;
  overflow-wrap: break-word;
  position: relative;
  margin: auto;
  width: 80%;
  padding-top: 16px;
}
#timeAndRoute{
  display: flex;
  justify-content: space-between;
  padding-top: 10px;
}
#removeIcon{
  padding-left: 26px;
}
#travelTime {
  font-family: Inter;
  font-size: 10px;
  color: rgba(207, 231, 202);
  opacity: 50%;
  position: relative;
}
#routeIcon{
  padding-right: 26px;
  opacity: 50%;
}
#routeIcon:hover{
  opacity: 100%;
  cursor: pointer;
}
#locationsContainer {


}
#locationTitle{
  font-family: Inter;
  font-size: 12px;
  color: rgba(207, 231, 202);
  text-align: center;
  padding-bottom:10px;
}
#locationPhotosContainer{
  padding-top: 10px;
  display: flex;
  justify-content: space-evenly;
  width: 280px;
  margin: auto;
  padding-bottom: 10px;
  /* position: absolute; */
 
}
.locationPhoto {
  border-radius: 50%;
  width: 60px;
  height: 60px;
}
.locationPhoto:hover{
  animation: bounce 1.5s;
  cursor: pointer;
  }

  @keyframes bounce{
    0%,  30%, 50% {
        transform: translateY(0);
    }
 
    25% {
        transform: translateY(-10px);
    }
    28%{
      transform: translateY(-10px);
    }
    42% {
        transform: translateY(-3px);
    }
    46% {
        transform: translateY(-3px);
    }
  
  }
  hr{
    width: 80%;
    color: rgba(207, 231, 202);
    opacity: 30%;
  }
  #locationDetailsPopUp{
    font-family: Inter;
    font-size: 12px;
    color: rgba(207, 231, 202);
    text-align: center;
  }
  #locationDetails{
    height: 60px;
    overflow: hidden;
    text-overflow: ellipsis;
    padding-right: 26px;
    padding-left: 26px;
    margin-bottom: 16px;
  }

.tourIcons{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  width:80%;
  margin: auto;
  height: 60px;

}
.tourIcon{
  opacity: 50%;
}
.tourIcon:hover{
  opacity: 100%;
  cursor: pointer;
}
</style>