package com.vsu.webmarket.web.controllers;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/data")
public class DataRestController {
    /*
    i: [0]
ProductId: [110402180436]
Title: [iPhone 6, 6 Plus Power On/Off, Gold, 4 in 1  0509]
ImageUrl: []
Price: [USD 15.99]
-----------------------------------------------------
i: [1]
ProductId: [110396359256]
Title: [iPhone 6 6s Front Screen Glass Lens, Black, Generic 0426]
ImageUrl: []
Price: [USD 10.55]
-----------------------------------------------------
i: [2]
ProductId: [110396354476]
Title: [iPhone 6 6s Front Screen Glass Lens, White, Generic]
ImageUrl: []
Price: [USD 10.0]
-----------------------------------------------------
i: [3]
ProductId: [110397095653]
Title: [For iPhone5S 5 5C LCD Replacement Touch Screen Full Assembly W/ Button Camera US]
ImageUrl: [http://thumbs2.sandbox.ebaystatic.com/pict/1103970956534040_0.jpg]
Price: [USD 13.99]
-----------------------------------------------------
i: [4]
ProductId: [110397096192]
Title: [For iPhone5S 5 5C LCD Replacement Touch Screen Full Assembly W/ Button Camera US]
ImageUrl: [http://thumbs1.sandbox.ebaystatic.com/pict/1103970961924040_0.jpg]
Price: [USD 13.99]
-----------------------------------------------------
i: [5]
ProductId: [110396025132]
Title: [Bike Motorcycle Handlebar Mount Magnetic Phone Holder iPhone XS X Samsung Galaxy]
ImageUrl: [http://thumbs1.sandbox.ebaystatic.com/pict/1103960251324040_0.jpg]
Price: [USD 11.99]
-----------------------------------------------------
i: [6]
ProductId: [110395947609]
Title: [iPhone 6 Apple 23]
ImageUrl: [http://thumbs2.sandbox.ebaystatic.com/pict/1103959476094040_0.jpg]
Price: [USD 11.0]
-----------------------------------------------------
i: [7]
ProductId: [110396061004]
Title: [Kanex iPhone Dock with Lightning Cable-K8PDOCK]
ImageUrl: [http://thumbs1.sandbox.ebaystatic.com/pict/1103960610044040_0.jpg]
Price: [USD 16.35]
-----------------------------------------------------
i: [8]
ProductId: [110396062169]
Title: [Cellet 43235-159755 Home Charger for iPhone X, 8+, 7+, 6+, 5/5s/5c iPad Pro/Air]
ImageUrl: [http://thumbs2.sandbox.ebaystatic.com/pict/1103960621694040_0.jpg]
Price: [USD 13.93]
-----------------------------------------------------
i: [9]
ProductId: [110395859529]
Title: [iPhone 6, 6 Plus 7, 7 Plus,SE Battery, 2900mAh, 616-00249]
ImageUrl: []
Price: [USD 19.98]
     */

    @PostMapping("/test")
    @ResponseBody
    public String getTestData(@RequestBody String json, HttpServletResponse response) {
        response.setStatus(200);
        return " { \"data\" : [" +
                "{" +
                "\"id\": 110402180436," +
                "\"title\": \"iPhone 6, 6 Plus Power On/Off, Gold, 4 in 1  0509\"," +
                "\"price\": \"USD 15.99\"" +
                "}," +
                "{" +
                "\"id\": 110395947609," +
                "\"title\": \"iPhone 6 Apple 23\"," +
                "\"image\": \"http://thumbs2.sandbox.ebaystatic.com/pict/1103959476094040_0.jpg\"," +
                "\"price\": \"USD 11.0\"" +
                "}," +
                "{" +
                "\"id\": 110396359256," +
                "\"title\": \"iPhone 6 6s Front Screen Glass Lens, Black, Generic 0426\"," +
                "\"price\": \"USD 10.55\"" +
                "}" +
                "]}";
    }
}
