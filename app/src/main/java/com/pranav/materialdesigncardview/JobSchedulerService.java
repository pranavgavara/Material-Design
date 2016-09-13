package com.pranav.materialdesigncardview;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by Pranav on 9/6/2016.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService implements YoutubeVideosLoadedListener {
    private JobParameters jobParameters;
    private String URL="https://www.googleapis.com/youtube/v3/videos?part=snippet,id&chart=mostPopular&time=today&maxResults=50&type=video&key=AIzaSyBnwD7oP-j38RUdYTQuV0C3rcE4_MHXNac";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
//        Toast.makeText(this, "onStartJob", Toast.LENGTH_SHORT).show();
        this.jobParameters=jobParameters;
        new TaskLoadYouTubeVideos(this,URL).execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public void onYouTubeVideosLoaded(ArrayList<singleRowYouTube_normal> listVideos) {
        jobFinished(jobParameters, false);

    }

//    private class MyTask extends AsyncTask<JobParameters, Void, JobParameters> {
//        JobSchedulerService jobSchedulerService;
//        private VolleySingleton volleySingleton;
//        private RequestQueue requestQueue;
//        String URL="https://www.googleapis.com/youtube/v3/videos?part=snippet,id&chart=mostPopular&time=today&maxResults=50&type=video&key=AIzaSyBnwD7oP-j38RUdYTQuV0C3rcE4_MHXNac";
//
//        ArrayList<Single_Row_YouTube_mostpopular_gson> list=new ArrayList<>();
//
//        MyTask(JobSchedulerService jobservice) {
//            this.jobSchedulerService = jobservice;
//            volleySingleton=VolleySingleton.getInstance();
//            requestQueue=volleySingleton.getmRequestQueue();
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected JobParameters doInBackground(JobParameters... jobParameterses) {
//
//            JSONObject response=Requestor.sendJSONRequest(requestQueue,URL);
//            ArrayList<Single_Row_YouTube_mostpopular_gson> Videolist=Parser_mostpopularList.parseJson(response);
//            MyApplication.getYouTubeDatabase().insertData(Videolist,true);
//            return jobParameterses[0];
//        }
//
//        @Override
//        protected void onPostExecute(JobParameters jobParameters) {
//            super.onPostExecute(jobParameters);
//            jobSchedulerService.jobFinished(jobParameters, false);
//        }
//
//        }
}

