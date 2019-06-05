package pl.piotrdawidziuk.recyclerviewjsondemo;

public class ExampleItem {
    private String  mImageUrl;
    private String mCreator;
    private int mLikes;

    public ExampleItem(String imageUrl, String creator, int likes) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mLikes = likes;
    }


    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public void setmCreator(String mCreator) {
        this.mCreator = mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }

    public void setmLikes(int mLikes) {
        this.mLikes = mLikes;
    }
}
