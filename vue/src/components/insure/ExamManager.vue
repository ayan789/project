<template>
  <div class="con">
    <div style="height: 300px;">
      <el-card class="box-card step-branch">
        <el-tree
          :props="props"
          :load="loadNode"
          lazy
          show-checkbox
          @check-change="handleCheckChange"
        >
        <span>
          <el-button
            type="text"
            size="mini"
            @click="() => append(data)">
            Append
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
        </el-tree>
      </el-card>
      <el-card class="box-card step-branch-main"></el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "examManager",
  data() {
    return {
      msg: "Hello examManager",
      props: {
          label: 'name',
          children: 'zones'
        },
        count: 1
    };
  },
   methods: {
      handleCheckChange(data, checked, indeterminate) {
        console.log(data, checked, indeterminate);
      },
      handleNodeClick(data) {
        console.log(data);
      },
      loadNode(node, resolve) {
        if (node.level === 0) {
          return resolve([{ name: 'region1' }, { name: 'region2' }]);
        }
        if (node.level > 3) return resolve([]);

        var hasChild;
        if (node.data.name === 'region1') {
          hasChild = true;
        } else if (node.data.name === 'region2') {
          hasChild = false;
        } else {
          hasChild = Math.random() > 0.5;
        }

        setTimeout(() => {
          var data;
          if (hasChild) {
            data = [{
              name: 'zone' + this.count++
            }, {
              name: 'zone' + this.count++
            }];
          } else {
            data = [];
          }

          resolve(data);
        }, 500);
      }
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.step-branch {
  width: 340px;
  height: 100vh;
  float: left;
  background-color: #fff;
}
.step-branch-main {
  width: 70vm;
  height: 100vh;
  margin-left: 350px;
  background-color: #fff;
  margin-top: 10px;
}
</style>
