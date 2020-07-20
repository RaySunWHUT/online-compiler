<template>
  <div class="home">
    <img class="banner" src="@/assets/back.jpg" alt="">
    <v-symbol></v-symbol>
    <div class="in">
      <pre>
        <textarea spellcheck="false" v-bind:style="lol" v-model="code" placeholder="please input your code here.."></textarea>      
      </pre>
    </div>

    <div class="note">

      <label v-if="result === null">
          <i>
            {{ tip }}
          </i>
      </label>

      <span v-else v-html="result">
            {{ result }}
      </span>

    </div>

    <el-button type="primary" @click="run" class="button1">运行</el-button>
  
  </div>
</template>

<script>

import http from '@/api/http';

import vSymbol from '@/components/Symbol.vue';

export default {

  name: 'Home',

  components: {

    vSymbol

  },
  
  data () {
  
    return {

      code: '',
      height: 350,
      result: null,
      rowOrder: 1,
      tip: 'your output will be here···'
    
    }
  
  },

  watch: {
  
    code (val) {

      if (this.code == '' || this.code == null) {
        this.result = null;
      }

    }
  
  },

  methods: {
      
      run() {

        var _this = this;

        http({

					// 假设后台需要的是表单数据这里你就可以更改
					headers: {

					"Content-Type": "application/json;charset=UTF-8"
					
					},

          method: 'post',
          
					url: 'http://localhost:8080/compile/pythonInterface',

					data: {

            code: _this.code

					},

					responseType: 'json'

        // 直接使用function, 而不使用箭头函数时, 不可以直接使用this
        // function有独立的作用域
				}).then(function (res) {

					var code = res.code;
          var info = res.info;
          var msg = res.msg;

					if (res.code == 200) {
            
            _this.$message.success(msg);

            _this.result = info.response;
            
					
					} else {
						
            _this.$message.warning(msg);

          }
          
        // 使用箭头函数时, 则可以直接使用this
        // 箭头函数没有独立的作用域
				}).catch((err) => {
          
          this.$message.error("global error!");
				
				});
      
      },

  },

  computed: {

    lol: function () {

        return {
            
            height: this.height + 'px',

        }

    }

  },

}
</script>

<style lang="less" scoped>

.button1 {

  font-weight: bold;
  font-size: 20px;
  position: absolute;
  top: 510px;
  left: 600px;

}


.in textarea {

  resize: none;
  word-break: break-all;
  border: 1px solid gray;
  border-radius: 1%;
  width: 550px;
  font-weight: bold;
  font-size: 15px;
  font-family: 'Courier New', Courier, monospace;
  background-color: aliceblue;
  position: absolute;
  top: 140px;
  left: 135px;

}


.note {

  width: 460px;
  height: 350px;
  border: 1.2px solid gray;
  position: absolute;
  top: 140px;
  left: 750px;
  border-radius: 1%;
  font-family: 'Courier New', Courier, monospace;

}


.note i {

  color: rgba(209, 204, 204, 0.74);
  font-weight: bold;
  font-size: 18px;
  position: absolute;
  left: 10px;

}


.note label {

  list-style: none;
  margin: 0;
  padding: 0;
  color: rgba(235, 235, 235, 0.74);
  font-weight: bold;
  font-size: 18px;

}


.note span {
  
  position: absolute;
  left: 0%;
  text-align: left;
  font-weight: bold;

  color: aliceblue;

}


.home {

  position: absolute;
  top: 0;
  left: 0;
  overflow: hidden;
  width: 100%;
  min-height: 100%;
  height: auto;
  .banner {

      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      z-index: -1;
  
  }
    
}

</style>
