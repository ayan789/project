import JSEncrypt from "jsencrypt";
var EncryptUtils = new JSEncrypt();
EncryptUtils.setPublicKey(
  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzfKrgzOisGDgpPBspXbAeqd7aDPxeP/zU275Rg64AAmvm8zl44RBtfi0eb+9Pt3mGCr37w2rWbI2xAwVcL+DSjSttSrh9ndW7mS5JND5GSL8mYw80fG+N1Mdl46iTAS6s5exaxscOrPJ4deuUWZFdH8ZYQjZmH6mIpVd34JhFpQIDAQAB"
);
export default EncryptUtils;