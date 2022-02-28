# Important
The following templates has been modified from version 5.2.1 of open-api-generator.

1. **typeInfoAnnotation.mustache**
  - Address this Issue: https://github.com/OpenAPITools/openapi-generator/issues/8559
2. **licenseInfo.mustache**
  - To override some licence headers
3. **api.mustache**
  - Remove extends of `ApiClient.Api `to avoid generate some utils class with the default okHttp client
  - Add @FeignClient to use with Spring



