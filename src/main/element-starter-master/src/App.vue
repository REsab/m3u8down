<template>
  <div id="app">
    <img src="./assets/logo.png" />
    <el-form ref="form" :model="form" label-width="80px" class="form">
      <el-form-item label="下载链接">
        <el-input v-model="form.urlm3u8" placeholder="粘贴下载链接"></el-input>
      </el-form-item>
      <el-form-item label="保存目录">
        <el-input v-model="form.path" value="C://m3u8JavaTest1"></el-input>
      </el-form-item>
      <el-form-item label="文件名">
        <el-input v-model="form.fileName" ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmitt">开始下载</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      form: {
        path: "C://m3u8JavaTest1",
        fileName: "Test1",
        urlm3u8:
          "https://cdn.extp.org/20200619/83970f6a35fa947359bbc9bcd382adeb.mp4/index.m3u8"
      }
    };
  },
  methods: {
    onSubmitt() {
      console.log("submit!");
      var path = this.form.path;
      var fileName = this.form.fileName;
      var urlm3u8 = this.form.urlm3u8;
 

      fileName = fileName + "_" + new Date().getTime();

      const form = this.form;
      const url = `/down122`;
      axios
        .get(url, {
          params: {
            // ...form
            path,
            fileName,
            urlm3u8
          }
        })
        .then(res => {
          console.log(res);
        });
    }
  }
};



</script>

<script>

        const socket = new WebSocket("ws://127.0.0.1:8078/websocket");
        socket.onopen = (ws) => {
            console.log(new Date().getTime() + " start ws... ")

        }
        socket.onmessage = (ws) => {
            console.log(new Date().getTime() + "ws: " + ws.data)

        }
        socket.onclose = (ws) => {
            console.log(new Date().getTime() + " close ws... ")

        }
        socket.onerror = (ws) => {
            console.log(new Date().getTime() + " ws error ")

        }


        setTimeout(() => {
            socket.send("send msg")
        }, 2000)


        setTimeout(() => {
            socket.close()
        }, 2000)


    </script>

<style>
#app {
  font-family: Helvetica, sans-serif;
  text-align: center;
  width: 800px;
  margin: 0 auto;
}
#app img {
  width: 150px;
}
</style>
