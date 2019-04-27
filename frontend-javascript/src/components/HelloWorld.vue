<template>
  <div>
    <h1>Teacher List</h1>
    <div id="search-term-name">
      <v-text-field
        v-model="searchName"
        label="by name"
        single-line
        hide-details>
        </v-text-field>
    </div>
      <div id="search-term-email"> 
        <v-text-field
        v-model="searchEmail"
        label="by email"
        single-line
        hide-details>
        </v-text-field>
      </div>
      <div id="search-btn">
        <v-btn small color="primary" @click="getDataFromApi">search</v-btn>
      </div>
    
      <div class="teacher-list">
        <v-data-table
        :headers="headers"
        :items="teachers"
        :pagination.sync="pagination"
        :total-items="totalTeachers"
        :loading="loading"
        class="elevation-1">
          <template v-slot:items="props">
            <td>{{ props.item.name }}</td>
            <td>{{ props.item.email }}</td>
            <td>{{ props.item.faculty.name }}</td>
          </template>
        </v-data-table>
      </div>  
  </div>
</template>

<script>
export default {

  data () {
    return {
      searchName:'John',
      searchEmail:'',
      headers: [
        {
          text: 'Name',
          sortable: false,
          value: 'name'
        },
        { text: 'Email', value: 'email' },
        { text: 'Faculty', value: 'faculty' }
      ],
      teachers: [],
      totalTeachers: 0,
      loading: true,
      pagination: {}

    }
  },
  watch: {
    pagination: {
      handler () {
        this.getDataFromApi()
          .then(data => {
            this.teachers = data.items
            this.totalTeachers = data.total
          })
      },
      deep: true
    }
  },
  mounted () {
    this.getDataFromApi()
      .then(data => {
        this.teachers = data.items
        this.totalTeachers = data.total
      })
  },
  methods: {
    getDataFromApi () {
      this.loading = true
      return new Promise((resolve, reject) => {

        let items=[];

        const { sortBy, descending, page, rowsPerPage } = this.pagination;
        
        fetch('api/teachers?filter=name:'+ this.searchName, {
          method: 'GET',
          headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            'crossDomain': 'true',
            'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
            'Access-Control-Allow-Credentials': 'true'
          } })
          .then(
            function (response) {
              if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status)
                return
              }

              response.json().then(function (data) {
                console.log(data)
                
                items=data
                
              })
            }
          )
          .catch(function (err) {
            console.log('Fetch Error :-S', err)
          })

          const total = items.length

          if (this.pagination.sortBy) {
            items = items.sort((a, b) => {
              const sortA = a[sortBy]
              const sortB = b[sortBy]

              if (descending) {
                if (sortA < sortB) return 1
                if (sortA > sortB) return -1
                return 0
              } else {
                if (sortA < sortB) return -1
                if (sortA > sortB) return 1
                return 0
              }
            })
          }

          if (rowsPerPage > 0) {
            items = items.slice((page - 1) * rowsPerPage, page * rowsPerPage)
          }

          setTimeout(() => {
            this.loading = false
            resolve({
              items,
              total
            })
          }, 1000)
      })
    }

  }
}
</script>
<style scoped>
.teacher-list {
  width:70%;
  margin-left: 20px;
  margin-top: 50px;
}

div#search-term-name {
    width: 15%;
    float: left;
    margin-left: 20px;
}

div#search-term-email {
    width: 15%;
    float: left;
    margin-left: 20px;
}
div#search-btn {
    margin-left: 15%;
  
   
}

</style>
