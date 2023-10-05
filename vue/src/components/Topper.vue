<template>
  <div id="topper">
    <div id="topArea">
      <router-link to="/" id="logo">pingo</router-link>

      
             <!-- dropdown menu -->
      <div class="toggle">

              <!-- toggle on click small user photo -->
        <img src="../assets/samantha.png" id="userPic" />
        <!-- <img src="../assets/samantha.png" id="userPic" onclick="toggleMenu()"/> -->

        <div class="menuWrap" id="menu">
          <div class="menu">
            <div class="userInfo">
              <h3 id="userName">Sam Doe</h3>
              <div id="changePhoto">
                <img src="../assets/samantha.png" id="largeUserPic" />
                <!-- <div id="addContainer">
                  <plusIcon/>
                </div> -->
              </div>
            </div>
            <hr />

            <a href="#" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>login/logout</p>
              <span>></span>
            </a>

            <a href="Account" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>edit profile</p>
              <span>></span>
            </a>

            <a href="MyToursPage" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>my trips</p>
              <span>></span>
            </a>
            <a href="/" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>new trip</p>
              <span>></span>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import userIcon from "../assets/userIcon.vue";

export default {
  components: { userIcon},
  name: "Topper",
};

// this isnt working.   error  'toggleMenu' is defined but never used but it 
// IS used in the img.  ?

// function toggleMenu() {

    //(function toggle A)
//   // let menu= document.getElementById("menu");
//   // menu.classList.toggle("openMenu");

    //(function toggle B)
//   var x = document.getElementById("menu");
//   if (x.style.display === "none") {
//     x.style.display = "block";
//   } else {
//     x.style.display = "none";
//   }
// }

// Close menu when clicking outside


</script>

<style scoped>

/* toggle display on and off til the function is fixed */
.toggle{
  /* display:none; */
}
#changePhoto{
  display: flex;
  z-index: 2;

}
#addContainer {
  display: flex;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  z-index: 3;
  position:absolute;
  margin-left: 60px;
  margin-top: 60px;
}
#largeUserPic {
  border-radius: 50%;
  width: 80px;
  margin-right: 15px;
}


#topper {
  position: fixed;
  top: 0;
  z-index: 100;
  width: 100%;
}
#topArea {
  border-radius: 0px 0px 20px 20px;
  background-image: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  padding: 17px 26px;
  /* align-items: flex-start; */
  height: 90px;
  display: flex;
  position: relative;
}

#logo {
  color: var(--mint, #cfe7ca);
  text-shadow: 0px -4px 4px rgba(0, 0, 0, 0.2);
  font-family: Orelega One;
  font-size: 48px;
  text-align: center;
  text-decoration: none;
  padding-top: 80px;
  z-index: auto;
  position: absolute;
  left: 50%;
  width: 100px;
  margin: -50px 0 0 -50px;
}

#userPic {
  overflow: hidden;
  border-radius: 50%;
  width: 26px;
  height: 26px;
  object-fit: cover;
  border: 2px solid var(--mint, #cfe7ca);
  margin-top: 65px;
  z-index: 3;
  position: absolute;
  right: 26px;
}


.menu {
  position: absolute;
  top: 100%;
  right: 5%;
  width: 380px;
  max-height: 600px;
  overflow: hidden;
  transition: max-height 0.5s;
}
.menu.openMenu {
  max-height: 600px;
}
.menu {
  background: #182935;
  padding: 20px;
  margin: 10px;
  border-radius: 20px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.4);
}
.userInfo {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
#userName {
  color: var(--mint, #cfe7ca);
  text-align: center;
  font-family: Orelega One;
  font-size: 30px;
  font-weight: 400;
  letter-spacing: 1px;
  text-transform: lowercase;
  margin: 0px;
}
.menu hr {
  border: 0;
  height: 1px;
  width: 100%;
  background: #cfe7ca85;
  margin: 15px 0 10px;
}
.menuLink {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #cfe7ca85;
  margin: 12px 0px;
  font-family: Inter;
  font-size: smaller;
}
.menuLink p {
  width: 100%;
}
.menuLink:hover p {
  /* width: 100%; */
  font-weight: 500;
  color: #cfe7ca;
}
.menuLinkIcon {
  margin-left: 10px;
  margin-right: 15px;
}
.menuLink span {
  transition: transform 0.5s;
}
.menuLink:hover span {
  transform: translateX(5px);
  color: #cfe7ca;
}
</style>

