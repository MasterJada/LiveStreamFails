package com.example.livestreamfails.networking


import com.example.livestreamfails.VideoModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup


class ApiHelper {
    val client = OkHttpClient()



    fun getList(page: Int =0 , postOrder: String = "top", postTimeFrame: String = "all", NSFW: Boolean = false ): ArrayList <String>{

       val url =  "https://livestreamfails.com/load/loadPosts.php?loadPostPage=$page" +
               "&loadPostMode=standard" +
               "&loadPostOrder=$postOrder" +
               "&loadPostTimeFrame=$postTimeFrame" +
               "&loadPostNSFW=${if(NSFW) 0 else 1}"
        val req = Request.Builder()
            .url(url)
            .get()
            .build()

        val res = client.newCall(req).execute()

        val regex = "https:\\/\\/livestreamfails\\.com/post\\/[0-9]+".toRegex()
        res.body()?.string()?.let {
           val matches = regex.findAll(it)
            val array = ArrayList<String>()
            array.addAll(matches.map { it.groupValues[0] })

            return array
        }
        return ArrayList()
    }

    fun getListJSoup(page: Int =0 , postOrder: String = "top", postTimeFrame: String = "all", NSFW: Boolean = false ): ArrayList <String>{

        val url =  "https://livestreamfails.com/load/loadPosts.php?loadPostPage=$page" +
                "&loadPostMode=standard" +
                "&loadPostOrder=$postOrder" +
                "&loadPostTimeFrame=$postTimeFrame" +
                "&loadPostNSFW=${if(NSFW) 0 else 1}"
        val doc = Jsoup.connect(url).get().select("div.post-card > a")
        val linkList = ArrayList<String>()
        doc.forEach {
            linkList.add(it.attr("href"))
        }
        print("")

        return linkList
    }

    fun getInfoFromPage(pageUrl: String):VideoModel{
        val doc = Jsoup.connect(pageUrl).get()
        val model = VideoModel()
        model.imageLink     =   doc.select("meta[property=og:image]").attr("content")
        model.videoLink     =   doc.select("meta[property=og:video]").attr("content")
        model.videoTitle    =   doc.select("meta[name=description]").attr("content")
        return model
    }
}