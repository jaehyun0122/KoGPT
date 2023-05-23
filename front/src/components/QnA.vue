<template>
<v-container>
  <v-row>
    <v-col>
      <v-textarea label="Label" variant="outlined" v-model="prompt"></v-textarea>
      <v-btn variant="outlined" @click="doQuestion">
        질문하기
      </v-btn>
    </v-col>
    <v-col>
      <div class="text-caption">
        문장 간결성. 높을 수록 간결함.
      </div>
      <v-slider
          v-model="slider2"
          :thumb-size="36"
          thumb-label="always"
      ></v-slider>
    </v-col>
  </v-row>

<div v-for="(data, index) in answerArr" :key="index">
  <v-row>
    <v-card width="400">
      <v-card-item>
        <v-card-title>Answer</v-card-title>
      </v-card-item>

      <v-card-text>
        {{data.text}}
      </v-card-text>
    </v-card>
    <v-btn v-if="answerArr.length > 0" @click="deleteAnswerRow(index)">삭제</v-btn>
  </v-row>
</div>

</v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: "QnA",
  data(){
    return{
      prompt: "",
      answerArr: [],
      slider2: 50,
    }
  },
  methods:{
    doQuestion(){
      axios.post("http://localhost:8080",{
        "prompt":this.prompt,
        "temperature":this.slider2
      }).then(response => {
        response.data.map(data=>{
          this.answerArr.unshift(data)
        })
      }).catch(error => {
        alert(error)
      })
    },

    deleteAnswerRow(index){
      this.answerArr = this.answerArr.splice(index, index)
    }
  }

}
</script>

<style scoped>

</style>