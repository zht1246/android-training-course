RecyclerView

ListView
GridView

##1. 在fragment_daily.xml布局中
加入一个RecyclerView
    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_daily_items"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </android.support.v7.widget.RecyclerView>

##2. 创建列表项的布局
   在layout/item_daily.xml
   之前需要先导入cardview这个包

   <?xml version="1.0" encoding="utf-8"?>

    <android.support.v7.widget.CardView
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:elevation="8dp"
        android:layout_margin="8dp"
        app:cardCornerRadius="2dp"
        >

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <TextView
                android:id="@+id/tv_daily_item_title"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:textColor="@android:color/black"
                android:textSize="18sp"
                android:padding="16dp"/>

            <ImageView
                android:id="@+id/iv_daily_item_image"
                android:layout_width="112dp"
                android:layout_height="96dp"
                android:scaleType="fitXY"

                android:layout_margin="16dp"
                android:src="@drawable/news_image"/>
        </LinearLayout>
    </android.support.v7.widget.CardView>

##3. 创建ViewHolder类来处理这个布局
public class DailyViewHolder extends RecyclerView.ViewHolder{

    TextView mTvTitle;
    ImageView mIvImage;

    public DailyViewHolder(View itemView) {
        super(itemView);
        mTvTitle = itemView.findViewById(R.id.tv_daily_item_title);
        mIvImage = itemView.findViewById(R.id.iv_daily_item_image);
    }
}
##4. 根据json数据的结构编写DailyListBean处理数据
public class DailyListBean implements Serializable{
    private Date date;

    private List<StoryBean> stories;

    private List<TopStoryBean> topStories;

    public List<StoryBean> getStories() {
        return stories;
    }
    public void setStories(List<StoryBean> stories) {
        this.stories = stories;
    }

    public List<TopStoryBean> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStoryBean> topStories) {
        this.topStories = topStories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //stories;
    /*
    *       "images": [
        "https:\/\/pic3.zhimg.com\/v2-9daa6b3fd2959ed37e773802727972b2.jpg"
      ],
      "type": 0,
      "id": 9666020,
      "ga_prefix": "011515",
      "title": "王思聪现实中是一个什么样的人？"*/
    public static class StoryBean{
        private List<String> images;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    /*
    *   "image": "https:\/\/pic4.zhimg.com\/v2-25e726517fb933035b902036d237c613.jpg",
      "type": 0,
      "id": 9666000,
      "ga_prefix": "011514",
      "title": "为什么请来了 Jessie J，《歌手》总导演洪涛还是哭红了眼？"*/
    public static class TopStoryBean{
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}



##5. 创建DailyAdapter用来控制数据在RecyclerView中的显示
public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DailyListBean.StoryBean> stories;

    Context context;

    public DailyAdapter(List<DailyListBean.StoryBean> stories,Context context) {
        this.stories = stories;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily,parent,false);
        DailyViewHolder dailyViewHolder = new DailyViewHolder(view);
        return dailyViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyViewHolder dvHolder = (DailyViewHolder)holder;
        dvHolder.mTvTitle.setText(stories.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}

##6.修改DailyFragment用来显示

导入fastjson的包
会解析json数据

public class DailyFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private DailyListBean dailyListBean;

    DailyAdapter dailyAdapter;

    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parseJSON();
        dailyAdapter = new DailyAdapter(dailyListBean.getStories(),getContext());
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        mRecyclerView = view.findViewById(R.id.rv_daily_items);
        mRecyclerView.setAdapter(dailyAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return view;
    }

    public void parseJSON(){
        try {
            InputStream inputStream = getContext().getAssets().open("news.json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text = new String(buf,"UTF-8");
            dailyListBean = JSON.parseObject(text, DailyListBean.class);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 