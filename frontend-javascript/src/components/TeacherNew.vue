<template>
  <div class="teacher-new-form">
    <h1>Add New Teacher</h1>
    <form novalidate="false">
    <div>
        <p v-if="errors.length">
            <b>Please correct the following error(s):</b>
            <ul>
            <li v-for="(error,index) in errors" :key="`error-${index}`">{{ error }}</li>
            </ul>
        </p>

        <div>
      <v-text-field
        v-model="name"
        label="add name"
        single-line
        hide-details>
        </v-text-field>
    </div>
      <div>
        <v-text-field
        v-model="email"
        label="add email"
        single-line
        hide-details>
        </v-text-field>
      </div>

        <v-layout wrap>
            <v-flex xs12>
                <v-combobox
                :items="faculty"
                label="Select a faculty"
                ></v-combobox>
            </v-flex>
        </v-layout>

        <div id="add-new-btn">
            <v-btn small color="primary" @click="addNewTeacher">Add New</v-btn>
        </div>

    </div>

</form>
  </div>
</template>
<script>
import axios from 'axios'

export default {

  data () {
    return {
      errors: [],
      name: null,
      email: null,
      faculty: ['History']
    }
  },
  methods: {
    addNewTeacher: function (e) {
      this.errors = []
      // let savedData = []

      if (!this.name) {
        this.errors.push('Name required.')
        return false
      } else {
        // save
        axios.post('api/teachers', {
          name: this.name,
          email: this.email,
          faculty:
          { id: 7,
            name: 'History' } // hardcoded for now
        })
          .then(response => (console.log(response.data)))
          .catch(error => console.log(error))
      }
    }

  }
}
</script>
<style scoped>
.teacher-new-form {
  width:15%;
  margin-left: 20px;
  margin-top: 50px;
}

</style>
