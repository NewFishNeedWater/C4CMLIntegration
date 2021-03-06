package com.sap.integration.constants;

public class Constants {

    public final static String APP_URL="https://dis.xixilz.com";

    public final static String DEMO_RESPONSE_FOR_C4C_ACTION ="[{\"resourceUnion\":{\"type\":\"chat\",\"displayIndex\":1,\"content\":\"Usually for this customer the best visit time is for 3:00pm to 4:00 pm\",\"hitRate\":0.65}},{\"resourceUnion\":{\"type\":\"chat\",\"displayIndex\":2,\"content\":\"Click this button and create a new visit plan for customer: Hawing Tech\",\"hitRate\":0.85}},{\"resourceUnion\":{\"type\":\"command\",\"displayIndex\":3,\"target\":\"Visit\",\"action\":\"create\",\"hitRate\":0.85,\"commandParas\":[{\"paraName\":\"date\",\"paraValue\":\"2018-03-29\",\"hitRate\":0.98},{\"paraName\":\"planStartTime\",\"paraValue\":\"14:00\",\"hitRate\":0.75},{\"paraName\":\"planEndTime\",\"paraValue\":\"15:00\",\"hitRate\":0.75},{\"paraName\":\"customerName\",\"paraValue\":\"Hawing Tech\",\"hitRate\":0.91}]}},{\"resourceUnion\":{\"type\":\"chat\",\"displayIndex\":4,\"content\":\"Here are some information you might found useful\",\"hitRate\":0.85}},{\"resourceUnion\":{\"type\":\"textInformation\",\"displayIndex\":5,\"resourceContents\":[{\"type\":\"title\",\"value\":\"Hawing Tech\",\"hitRate\":0.91},{\"type\":\"text\",\"value\":\"Sales Order successful rate since 2018: 44%\",\"hitRate\":0.53},{\"type\":\"text\",\"value\":\"Most Frequently visited products:\",\"hitRate\":0.53},{\"type\":\"text\",\"paraName\":\"Material: PLUMP 320, Number of quotated：18 \",\"hitRate\":0.65},{\"type\":\"text\",\"paraName\":\"Material: PLUMP 450, Number of quotated：12 \",\"hitRate\":0.65}]}},{\"resourceUnion\":{\"type\":\"command\",\"target\":\"Opportunity\",\"displayIndex\":2,\"action\":\"create\",\"hitRate\":0.65,\"commandParas\":[{\"paraName\":\"materialID\",\"paraValue\":\"PLUMP-232\",\"action\":\"add\",\"hitRate\":0.42},{\"paraName\":\"customerName\",\"paraValue\":\"Hawing Tech\",\"hitRate\":0.91}]}}]";

    public final static String NL_API_URL= "https://api.recast.ai/build/v1/dialog";

    public final static String NL_API_AUTHORIZATION="Token cc808a4996ec489278238113fa40d973";

}
