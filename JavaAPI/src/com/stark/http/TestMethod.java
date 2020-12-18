package com.stark.http;

public class TestMethod {
    public TestMethod()
    {
        try
        {
            SiteInfoBean bean = new SiteInfoBean(
                    "http://127.0.0.1/ota/sdm660_64-ota-eng-debug-20201113_A.jason.zip",
                    "E:", "sdm660_64-ota-eng-debug-20201113_A.jason.zip", 3);

            SiteFileFetch fileFetch = new SiteFileFetch(bean, new IStatusCallBack() {
                @Override
                public void call(int statusCode, int progress) {
                    System.out.println(statusCode+","+progress);
                }
            });
            fileFetch.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new TestMethod();
    }
}