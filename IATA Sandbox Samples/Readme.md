# ONE Order sandbox test samples


## Getting Started
- Use Postman
- import the latest json collection
- do not forget to set you API Key 

### Prerequisites

In order to use the ONE Order sandbox test samples, you need to :

- Request for an API Key in order to use IATA NDC and ONE Order APIs sandboxes - http://iata.mashery.com/member/register
- set your API key as a global variable as expected by the samples headers ({{Authorization-key}}) - (Postman documentation: https://www.getpostman.com/docs/postman/environments_and_globals/variables)

- look at the test guide : [ONE Order Sandbox How_to Guide](./ONE%20Order%20Sandbox%20How_to%20Guide.pdf)
- look at the sandbox content : http://ndc.developer.iata.org/docs/ONE_Order_Sandbox_Brochure_ZEUS_Airlines_Z9.pdf
- look at other information about the sandbox here: http://ndc.developer.iata.org/OO_sandbox.html


- use github samples in this folder


### Service Types and details
[List of services details](./ZeusServicesAug2017.xlsx)

When using per example IATA_ServiceDeliveryRQ with Service type:
```
<ServiceType>
	<Code>BAG</Code>
</ServiceType>
```

Please use the following list of codes:
- MEL : Meal
- CON : Wifi
- BAG : Bag services
- GRN : Lounge Pass
- SET : Seat selection
- LNP : Lounge Pass
- FTR : Fast Track
- GRN : Parking Service
- CRE : Car Rental
- TRS : Ground Transport Concierge
- VIP : VIP services

