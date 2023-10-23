<template>
  <div class="locationInfo">
    <div id="name">
      {{ name }}
    </div>
    <div id="photoAndInfo">
      <img id="locPhoto" v-bind:src="photos[0].getUrl()" />
      <!-- <aside v-for="attribution in photos[0].html_attributions" v-bind:key="attribution.id">
                Photo Credit: <span v-html="attribution"></span><br>
            </aside> -->

      <div class="contact-details" id="textContainer">
        <p id="address">{{ address }}</p>
        <p id="phone" v-show="phoneNumber">{{ phoneNumber }}</p>
      </div>
      <div id="icons">
        <remove-icon id="remove"></remove-icon>
        <call-icon id="call"></call-icon>
        <a id="website" v-show="website" v-bind:href="website"
          ><website-icon></website-icon
        ></a>
        <div>
            {{ ratings }}
            <star-icon id="stars"></star-icon>
        </div>
        
      </div>
    </div>
  </div>
</template>

<script>
import callIcon from "../assets/callIcon.vue"
import RemoveIcon from "../assets/removeIcon.vue";
import StarIcon from '../assets/starIcon.vue';
import websiteIcon from "../assets/websiteIcon.vue";

export default {
  components: { websiteIcon, RemoveIcon, callIcon, StarIcon},
  name: "LandmarkInfo",
  props: {
    name: String,
    placeId: String,
    address: String,
    reviews: Object,
    photos: {
      type: Array,
      default: () => [
        {
          getUrl: () => "",
        },
      ],
    },
    phoneNumber: String,
    ratings: Object,
    website: String,
  },
  methods: {
    defaultUrl() {
      return [
        {
          getUrl: () => "",
        },
      ];
    },
  },
};
</script>

<style scoped>
#photoAndInfo {
  display: flex;
  justify-content: space-between;
}
#name {
  color: rgba(207, 231, 202);
  font-family: Inter;
  font-size: 14px;
  letter-spacing: 1.2px;
  background-color: transparent;
  text-decoration: none;
  text-transform: lowercase;
  text-align: left;
  border: none;
  /* width: 80%;
  min-width: 280px;
  max-width: 460px; */
  position: relative;
  top: 0px;
  left: 26px;
  overflow-wrap: break-word;
}
#icons {
  width: 80%;
  height: 20px;
  position: relative;
  top: 100px;
  display: flex;
  justify-content: space-between;
  left: 26px;
}
#website {
  opacity: 50%;

}
#website:hover {
  opacity: 100%;
}
#remove {
  opacity: 50%;

}
#remove:hover {
  opacity: 100%;
}
#call{
  opacity: 50%;
 
}
#call:hover{
    opacity:100%
}
#stars{
    opacity: 50%;
}
#stars:hover{
    opacity:100%;
}
#locPhoto {
  border-radius: 50%;
  height: 70px;
  min-height: 70px;
  min-width: 70px;
  width: 70px;
  right: 26px;
  top: 40px;
  position: absolute;
}
.locationInfo {
  width: 80%;
  max-width: 500px;
  min-width: 200px;
}
.contact-details {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 11px;
  font-style: normal;
  font-weight: 400;
  line-height: 16px; /* 166.667% */
  letter-spacing: 1.4px;
  text-decoration: none;
  text-align: left;
  /* width: 360px; */
  min-width: 200px;
  position: absolute;
  left: 26px;
}
#textContainer {
  flex: 1;
  margin-right: 110px;
}
#address {
  width: 100%;
}
</style>