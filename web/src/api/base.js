// export const BASE_URL = "http://192.168.0.116:9000/api"

const SWAGGER = "/swagger-ui.html";

export const SWAGGER_ALL =process.env.VUE_APP_BASE_API+ SWAGGER;


export const SWAGGER_BMS = process.env.VUE_APP_BASE_API + "/bms" +SWAGGER;

export const SWAGGER_UMS = process.env.VUE_APP_BASE_API + "/ums" +SWAGGER;
