<template>
    <div class="dropdown">
        <!-- <button class="dropbtn">Menu with buttons</button>
        <div class="dropdown-content">
        <button @click="$router.push('/')">Home</button>
        <button @click="$router.push('/myToursPage')">My City Tours</button>
        <button @click="$router.push('/planNewTourPage')">New City Tours</button>
        <button @click="$router.push('/login')">login/logout</button>
        <button @click="$router.push('/landing')">Landing</button>
      </div>
      <p></p> -->
  
  
    <button class="dropbtn">Menu href</button>
    <div class="dropdown-content">
      <a href="login">login</a>
      <a href="">home</a>
      <a href="myToursPage">my trips</a>
      <a href="planNewTourPage">new daytrip</a>
      <a href="logout">logout</a>
      </div>  
      </div>  
  </template>
    
    
    <script>
  export default {
    name: "navigationBar",
  };
  
  </script>
    
    <style>
  .dropbtn {
    background-color: #04aa6d;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
  }
  .dropdown {
    position: relative;
    display: inline-block;
  }
  .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    z-index: 1;
  }
  .dropdown-content a {
    color: rgb(36, 239, 9);
    padding: 12px 16px;
    text-decoration: none;
    display: block;
  }
  
  .dropdown-content a:hover {
    background-color: #ddd;
  }
  
  .dropdown:hover .dropdown-content {
    display: block;
  }
  
  .dropdown:hover .dropbtn {
    background-color: #3e8e41;
  }
  
  
  </style>