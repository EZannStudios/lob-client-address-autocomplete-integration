## Challenge 1
### Simple API integration and data output through an endpoint.  
- Input: Part of a real address in the US. City, state and zipCode as optional values. 
- Output: Complete addresses, containing City, State and ZipCode. 
- SDK: https://github.com/lob/lob-java
- ENDPOINT: https://docs.lob.com/#operation/autocompletion
- Api-Key: `live_pub_a15953d6b86c7028cbf7605ed360eee`
- Postman: export the collection/request used to call the endpoint and put it into the `/postman` directory.

- Changed the Api-Key because I was getting this error when trying to autocomplete an address
![Capture](https://github.com/EZannStudios/lob-client-address-autocomplete-integration/assets/69906433/9abb02b2-4f07-4780-8213-3bb08854c98b)

Got a new account at lob.com and generated new keys<br />
Live:<br />
live_pub_e71fae5373ec3c938e7500cc0afdb1f<br />
Test:<br />
test_pub_3c9c401e55927501b9ce3c443bb6b3a
