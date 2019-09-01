<template>
    <div class="background" id="home">
        <div class="over"></div>
        <span>欢迎登录天外飞仙</span>
    </div>
</template>

<script>
	export default {
		name: "index",
		mounted() {
			let home = document.getElementById("home");
			// console.log(home.parentElement.clientHeight);
			home.style.height = home.parentElement.clientHeight + 'px';
			console.log(home.children);
			let div = home.children[0];
            div.style.height = home.parentElement.clientHeight + 'px';
		}
	}
</script>

<style scoped>
    .background {
        background: url("../../icons/bg.png") repeat 100% 100%;
        text-align: center;
        position: relative;
        /*filter: brightness(0.8);*/
    }
    .background>.over{
        width: 100%;
        position: absolute;
        left: 0;
        top: 0;
        background: rgba(0,0,0,0.3);
    }
    .background > span {
        color: #fff;
        font-size: 50px;
        display: inline-block;
        width: 400px;
        height: 60px;
        position: absolute;
        left: 50%;
        top: 50%;
        margin-left: -200px;
        margin-top: -30px;
        line-height: 60px;
    }
</style>
