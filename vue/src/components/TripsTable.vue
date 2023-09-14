<template>
  <table id="tripsTable">
    <tr>
      <th>city</th>
      <th>date</th>
      <th>starting location</th>
      <th>ending location</th>
      <th>number of stops</th>
    
    </tr>
    <tr>
      <td>san francisco</td>
      <td>1/1/20</td>
      <td>san mateo</td>
      <td>tiburon</td>
      <td>3</td>
    </tr>
  </table>
</template>

<script>
  export default {
    name: "TripsTable",
  }
</script>

<style scoped>
  #tripsTable {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    border-collapse: collapse;
    width: 100%;
    margin-top:10px;
    background-color: #fffefe;
  }

  #tripsTable td, #tripsTable th {
    padding: 4px;
    text-align: center;
  }

  #tripsTable tr:nth-child(even) {
    background-color: #f2f2f2;
  }

  #tripsTable tr:hover {
    background-color: #ddd;
  }
  
  #tripsTable th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #ffab3d;
    color: white;
    text-align: center;
    text-shadow: 2px 2px 5px rgba(15, 12, 6, 0.164);
  }
</style>