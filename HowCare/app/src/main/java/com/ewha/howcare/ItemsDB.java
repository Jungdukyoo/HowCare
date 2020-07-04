package com.ewha.howcare;

import static com.ewha.howcare.DetailAdapter.TYPE_CONTENTHEAD;
import static com.ewha.howcare.DetailAdapter.TYPE_CONTENTITEM;
import static com.ewha.howcare.DetailAdapter.TYPE_CONTENTNUM;
import static com.ewha.howcare.DetailAdapter.TYPE_CONTENTSUBITEM;
import static com.ewha.howcare.DetailAdapter.TYPE_EMPTY;
import static com.ewha.howcare.DetailAdapter.TYPE_EMPTY3DP;
import static com.ewha.howcare.DetailAdapter.TYPE_GIFIMAGE;
import static com.ewha.howcare.DetailAdapter.TYPE_HEAD;
import static com.ewha.howcare.DetailAdapter.TYPE_Heimlich;
import static com.ewha.howcare.DetailAdapter.TYPE_IMAGE;
import static com.ewha.howcare.DetailAdapter.TYPE_MOUTH;
import static com.ewha.howcare.DetailAdapter.TYPE_NEWIMAGE;
import static com.ewha.howcare.DetailAdapter.TYPE_PRECONTENT;

import java.util.ArrayList;

public class ItemsDB {
  private ArrayList<ArrayList<ArrayList<ContentItem>>> dbList = new ArrayList<ArrayList<ArrayList<ContentItem>>>();
  private int slide_itemnum = 1;
  private int[] current_page = {1,1,1,1,1,1,1,1,1};

  public static final int TOTAL_PAGE = 8;
  public static final int[] PAGE_ITEMNUM = {0, 3, 3, 2, 4, 4, 1, 2, 4};
  private static final ItemsDB ourInstance = new ItemsDB();

  public static ItemsDB getInstance() {
    return ourInstance;
  }

  private ItemsDB() {
  //TODO: 1부터 시작하게 만들기 아이템들도 1~~

    ArrayList<ArrayList<ContentItem>> tabItem = new ArrayList<ArrayList<ContentItem>>();
    ArrayList<ContentItem> item = new ArrayList();

    dbList.add(tabItem); //0번째 아이템
    tabItem.add(item); //0번째 아이템

    item.add(new ContentItem("1. 식사는 즐거워야 한다", TYPE_HEAD));
    item.add(new ContentItem(R.drawable.image001, TYPE_IMAGE));

    item.add(new ContentItem("노인에게 먹는 일은 가장 큰 즐거움 중 하나이며 식사 시간은 기다려지는 시간입니다.",TYPE_PRECONTENT));
    item.add(new ContentItem("그래서, 식사는 단순히 신체에 영양을 공급하는 행위가 아닙니다.",TYPE_PRECONTENT));
    item.add(new ContentItem("맛있는 음식을 먹으면 활력도 생기고 기분도 밝아집니다.",TYPE_PRECONTENT));
    item.add(new ContentItem("즐거운 식사 분위기를 \n"
        + "만들 수 있는 방법", TYPE_CONTENTHEAD));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식단을 설명하면 식욕이 증가됩니다.",R.mipmap.o_s1));
    item.add(new ContentItem("예) “오늘은 늙은 호박을 넣은 꽃게탕이에요.”", TYPE_CONTENTITEM,"식사를 눈으로 즐길 수 있도록 음식의 내용물이나 조리법 등을 설명합니다.",R.mipmap.o_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"특히 유동식과 같이 소재를 알 수 없는 음식은 반드시 내용을 설명합니다.",R.mipmap.o_s3));

    tabItem.add(item);

    item = new ArrayList();
    item.add(new ContentItem("2. 입으로 먹는 것이 중요하다", TYPE_HEAD));
    item.add(new ContentItem(R.drawable.image002, TYPE_IMAGE));
    item.add(new ContentItem("입으로 먹으면 활기가 생깁니다.\n입으로 먹으면 상체의 운동을 포함해 전신을 움직이게 하고 활력을 불러 일으킵니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("“맛있는 냄새다” “맛있겠다”라는 감각은 대뇌가 일하고 있다는 것입니다.", TYPE_CONTENTITEM,"식욕",R.mipmap.o_s1));
    item.add(new ContentItem("음식물을 씹으면 침 분비가 되고 어깨 윗 부분의 근육을 움직이게 합니다.", TYPE_CONTENTITEM,"씹는 것",R.mipmap.o_s2));
    item.add(new ContentItem("삼킴 근육의 기능을 유지시킬 수 있습니다.", TYPE_CONTENTITEM,"삼킴",R.mipmap.o_s3));
    item.add(new ContentItem("소화, 흡수와 관련된 장기를 움직여 줍니다.", TYPE_CONTENTITEM,"소화",R.mipmap.o_s4));
    item.add(new ContentItem("화장실에 가면서 하체 운동이 가능합니다.", TYPE_CONTENTITEM,"배설",R.mipmap.o_s5));

    tabItem.add(item);

    item = new ArrayList();
    item.add(new ContentItem("3. 식사 전 준비사항", TYPE_HEAD));
    item.add(new ContentItem("식사는 침대보다 식당에서 함께 하는 것이 좋습니다.", TYPE_PRECONTENT));
    item.add(new ContentItem(R.mipmap.o_dot,"식당으로 이동하는 과정에서 자연스럽게 활동량이 증가합니다.",TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.o_dot,"함께 식사하면 즐겁게 식사할 수 있습니다.",TYPE_CONTENTSUBITEM));
    item.add(new ContentItem("식사 환경은 청결하고 쾌적해야 합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem(R.mipmap.o_dot,"잘 세탁된 밝은 색상의 식탁보",TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.o_dot,"밝은 조명",TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.o_dot,"가벼운 음악 등",TYPE_CONTENTSUBITEM));

    item.add(new ContentItem("식사 전 준비", TYPE_CONTENTHEAD));
    item.add(new ContentItem("식사 중에 요의나 변의로 인해 식욕에 방해되지 않도록 미리 배설을 유도합니다.", TYPE_CONTENTITEM,"배설확인",R.mipmap.o_s1));
    item.add(new ContentItem("대상자와 돌봄제공자는 식사 전에 물이나 물수건으로 손을 닦도록 합니다.", TYPE_CONTENTITEM,"손 닦기",R.mipmap.o_s2));
    item.add(new ContentItem("대상자가 동의하면 식사용 앞치마나 타월 등을 사용합니다.", TYPE_CONTENTITEM,"흘림 방지",R.mipmap.o_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"상차림",R.mipmap.o_s4));
    item.add(new ContentItem(R.mipmap.o_dot,"치매 노인은 상차림의 작은 변화에도 당황할 수 있으니 가급적 매일 똑같이 세팅하는 것이 좋습니다.",TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.o_dot,"치매노인은 음식이 보이지 않아 먹지 않을 수도 있으므로 음식은 중앙에 놓습니다.",TYPE_CONTENTSUBITEM));

    item.add(new ContentItem("식사 전 고려사항", TYPE_CONTENTHEAD));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"습관 및 기호",R.mipmap.o_s1));
    item.add(new ContentItem(R.mipmap.o_dot,"평소 식사 시간, 식사량, 식사 끝나는 시간 등의 식사 습관을 파악합니다.", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.o_dot,"어떤 음식을 좋아하는지 파악하는 것이 좋습니다.", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem("신체마비의 위치와 정도, 씹기, 삼키기 능력, 의치 여부, 정신 상태 등을 파악하여 도와드립니다.", TYPE_CONTENTITEM,"식사 지원 필요도",R.mipmap.o_s2));
    item.add(new ContentItem("식당까지 이동 방법, 식당의 온도, 환기 등 환경을 정돈합니다.", TYPE_CONTENTITEM,"식사 환경",R.mipmap.o_s3));
    item.add(new ContentItem("하루 섭취량 제한, 특수식(저염식, 당뇨식) 및 기타 주의사항이 있는지 확인합니다.", TYPE_CONTENTITEM,"식사의 제한사항 여부",R.mipmap.o_s4));

    tabItem.add(item);
    dbList.add(tabItem);

    tabItem = new ArrayList<>();
    item = new ArrayList<>();
    tabItem.add(item); //0번째 아이템

    item = new ArrayList<>();
    item.add(new ContentItem("1. 식사도구의 중요성", TYPE_HEAD));
    item.add(new ContentItem("", TYPE_CONTENTITEM,  "스스로 식사를 하면 성취감을 느껴 식사가 즐거워집니다. 노인 스스로 식사를 할 수 있도록 지원할 필요가 있습니다.",R.mipmap.o_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,  "마비가 있거나 근력이 약해져 있어 손을 잘 쓸 수 없으면 음식물을 입으로 가져갈 수 없어 식사가 스트레스가 됩니다.",R.mipmap.o_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM,  "최근에는 노인의 신체기능 상태를 고려한 식사 도구들이 많이 제공되고 있습니다.",R.mipmap.o_s3));

    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("2. 숟가락/젓가락", TYPE_HEAD));
    item.add(new ContentItem(R.drawable.newimage003, TYPE_NEWIMAGE));
    item.add(new ContentItem("고무로 만들어진 두꺼운 손잡이는 쥐는 힘이 약한 사람도 떨어뜨리지 않고 잡을 수 있습니다.", TYPE_CONTENTITEM,"손잡이가 두꺼운 숟가락",R.mipmap.o_s1));
    item.add(new ContentItem(R.drawable.newimage004, TYPE_NEWIMAGE));
    item.add(new ContentItem("섬세한 조작이 가능합니다.", TYPE_CONTENTITEM,"핀셋형 젓가락",R.mipmap.o_s2));
    item.add(new ContentItem(R.drawable.newimage005, TYPE_NEWIMAGE));
    item.add(new ContentItem("끝부분에 달린 용수철 탄성때문에 쉽게 뜨고 집을 수 있습니다.", TYPE_CONTENTITEM,"다기능 수저",R.mipmap.o_s3));


    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("3. 식기류", TYPE_HEAD));
    item.add(new ContentItem(R.drawable.newimage006, TYPE_NEWIMAGE));
    item.add(new ContentItem("접시 내부 한쪽이 깊숙이 기울어져 있어 음식물을 뜨기가 쉽습니다.", TYPE_CONTENTITEM,"경사진 접시",R.mipmap.o_s1));
    item.add(new ContentItem("잡고 들기 쉽습니다.", TYPE_CONTENTITEM,"손잡이가 달린 그릇",R.mipmap.o_s2));
    item.add(new ContentItem("코가 닿는 부분이 잘려 있기 때문에 고개를 많이 젖히지 않고도 마실 수 있습니다.", TYPE_CONTENTITEM,"경사진 컵",R.mipmap.o_s3));
    item.add(new ContentItem("실리콘 재질의 지지대로 그릇을 바닥에 밀착시켜 쏟아지지 않게 합니다.", TYPE_CONTENTITEM,"밥공기 지지대",R.mipmap.o_s4));
    item.add(new ContentItem(R.drawable.newimage007, TYPE_NEWIMAGE));
    item.add(new ContentItem("물이나 음료수를 흘리지 않게 합니다.", TYPE_CONTENTITEM,"빨대 달린 컵",R.mipmap.o_s5));
    item.add(new ContentItem("매트 위 식기가 미끄러지지 않아 한 손으로도 음식을 쉽게 먹을 수 있도록 합니다.", TYPE_CONTENTITEM,"미끄럼 방지 식탁 매트",R.mipmap.o_s6));

    tabItem.add(item);

    dbList.add(tabItem);

    tabItem = new ArrayList<>();
    item = new ArrayList<>();
    tabItem.add(item); //0번째 아이템


    item = new ArrayList<>();
    item.add(new ContentItem("1. 바른자세의 중요성", TYPE_HEAD));
    item.add(new ContentItem("가장 좋은 자세는 앉은 자세입니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("음식물이 자연스럽게 식도로 내려가기 때문입니다.  앉은 자세로 식사하면 음식을 눈으로 확인하기 때문에 식욕을 가장 잘 느낄 수 있습니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("따라서 휠체어에서라도 앉은 자세를 유지하면서 식사를  하는 것이 아주 중요합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem(R.drawable.image008, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "턱을 당기고 몸을 살짝 앞으로 숙입니다.",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "되도록 등받이가 수직인 의자를 골라 깊숙이 앉습니다.",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "식탁 높이는 팔꿈치를 테이블에 올렸을 때 직각이 되는 정도가 좋습니다.",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "양쪽 발 뒤꿈치가 바닥에 잘 닿아야 합니다.",R.mipmap.g_s4));
    item.add(new ContentItem("",TYPE_EMPTY3DP));
    item.add(new ContentItem("",TYPE_EMPTY3DP));
    item.add(new ContentItem(R.drawable.image009, TYPE_IMAGE));
    item.add(new ContentItem("음식물이 기도로 들어가 사레 들릴 수 있습니다.", TYPE_CONTENTITEM, "허리를 많이 굽힌 자세",R.mipmap.o_s1));
    item.add(new ContentItem("걸터 앉게 되면 발을 뻗게 되고 몸이 긴장하여 음식이 먹기 힘들어집니다.", TYPE_CONTENTITEM, "의자 앞쪽으로 걸터 앉은 자세",R.mipmap.o_s2));

    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("2. 상황별 바른 자세", TYPE_HEAD));
    item.add(new ContentItem("침대", TYPE_CONTENTHEAD));
    item.add(new ContentItem(R.drawable.newnewimage011, TYPE_NEWIMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "식사 할 때는 반드시 침대에서 일어나 앉거나 상체를 똑바로 일으켜 먹도록 합니다. 침상머리만 올려 상체를 일으키면 앞으로 숙이는 자세는 취하기 어렵기 때문입니다.",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "등을 곧게 펴고 턱을 당긴 자세를 합니다.",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "침대에 걸터 앉아 식사할 때는 양 발이 바닥에 닿아야 합니다.",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "누운 상태로 식사할 때는 머리를 올리고 얼굴을 옆으로 돌려 베개로 대준 후 음식물을 섭취할 수 있도록 자세를 취합니다.",R.mipmap.g_s4));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "한쪽 마비가 있는 경우 건강한 쪽이 아래로 오도록 하여 옆으로 돌려 누운 자세를 취합니다.",R.mipmap.g_s5));
    item.add(new ContentItem(R.drawable.image012, TYPE_IMAGE));
    item.add(new ContentItem("앞으로 숙이는 자세가 충분히 되지 않기 삼키기 어렵고 음식물 확인이 어렵습니다.", TYPE_CONTENTITEM, " 침상머리를 충분히 높이지 않은 자세",R.mipmap.o_s1));

    item.add(new ContentItem("휠체어", TYPE_CONTENTHEAD));
    item.add(new ContentItem(R.drawable.image013, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "등받이가 먼 경우 쿠션을 대주어 상체를 앞으로 숙이기 편하게 해줍니다.",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "브레이크를 걸어 휠체어가 움직이지 않게 합니다.",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "발판을 위로 올려 양쪽 발뒤꿈치가 바닥에 잘 닿아야 합니다.",R.mipmap.g_s3));

    tabItem.add(item);
    dbList.add(tabItem);

    tabItem = new ArrayList<>();
    item = new ArrayList<>();
    tabItem.add(item); //0번째 아이템


    item = new ArrayList<>();
    item.add(new ContentItem("1. 목운동", TYPE_HEAD));
    item.add(new ContentItem("목운동을 하여 어깨와 목의 긴장을 풀어주면 혀나 목의 움직임이 한결 부드러워지게 됩니다.", TYPE_PRECONTENT));
    item.add(new ContentItem(R.raw.newgif01, R.drawable.gifthumb01, TYPE_GIFIMAGE));
    item.add(new ContentItem("고개를 오른쪽으로 천천히 돌려 3초간 유지합니다. 이번엔 똑같이 반대쪽으로 돌려 3초간 유지합니다. 제자리로 돌아옵니다.", TYPE_CONTENTITEM, "고개 양옆으로 돌리기",R.mipmap.o_s1));
    item.add(new ContentItem(R.raw.newgif02, R.drawable.gifthumb02, TYPE_GIFIMAGE));
    item.add(new ContentItem("목을 오른쪽으로 천천히 기울여 3초간 유지합니다. 이번엔 똑같이 반대쪽으로 기울여 3초간 유지합니다. 제자리로 돌아옵니다", TYPE_CONTENTITEM, "목 양옆으로 기울이기",R.mipmap.o_s2));
    item.add(new ContentItem(R.raw.newgif03, R.drawable.gifthumb03, TYPE_GIFIMAGE));
    item.add(new ContentItem("고개를 숙여 3초간 유지합니다. 이번엔 반대로  뒤로 젖혀 3초간 유지합니다. 제자리로 돌아옵니다.", TYPE_CONTENTITEM, "고개 위아래로 움직이기",R.mipmap.o_s3));
    item.add(new ContentItem(R.raw.newgif04, R.drawable.gifthumb04, TYPE_GIFIMAGE));
    item.add(new ContentItem("양쪽 어깨를 으쓱하는 것처럼 천천히 올려 3초간 유지합니다. 반대로  천천히 아래로 떨구어 3초간 유지합니다. 제자리로 돌아옵니다.", TYPE_CONTENTITEM, "어깨 위로 들었다 내리기",R.mipmap.o_s4));

    tabItem.add(item);


    item = new ArrayList<>();
    item.add(new ContentItem("2. 입체조", TYPE_HEAD));
    item.add(new ContentItem("입의 움직임이 좋을 수록 씹고 삼키는 것이 수월해집니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("입체조는 혀와 볼의 움직임을 유연하게 하고 근력을 향상시켜 음식이 그냥 넘어가는 것을 막습니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("침샘을 자극해 침이 나올 수 있게 도와줍니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("입체조는 빠르게 하지 않고 정확히 하는 것이 중요합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("입운동", TYPE_CONTENTHEAD));
    item.add(new ContentItem("입을 크게 벌리고 3초가 유지하고 다시 입을 다뭅니다. 이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "입 크게 벌리기 ",R.mipmap.o_s1));
    item.add(new ContentItem(R.raw.newgif05, R.drawable.gifthumb05, TYPE_GIFIMAGE));
    item.add(new ContentItem("입을 다문 상태에서 미소 짓 듯이 양 입술의 꼬리를 옆으로 잡아당겨 3초간 유지하고 다시 입을 다뭅니다. 이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "양쪽 입꼬리 당기기",R.mipmap.o_s2));
    item.add(new ContentItem(R.raw.newgif06, R.drawable.gifthumb06, TYPE_GIFIMAGE));
    item.add(new ContentItem("양입술을 뽀뽀하듯 최대한 앞으로 내밀어 3초간 유지하고 다시 편안하게 입을 다뭅니다. 이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "입술 오므리기",R.mipmap.o_s3));
    item.add(new ContentItem(R.raw.newgif07, R.drawable.gifthumb07, TYPE_GIFIMAGE));
    item.add(new ContentItem("양쪽 볼에 공기를 넣어 최대한 부풀려 3초간 유지한 후 공기를 다시 내보냅니다. 이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "양쪽 볼 부풀리기",R.mipmap.o_s4));
    item.add(new ContentItem(R.raw.newgif08, R.drawable.gifthumb08, TYPE_GIFIMAGE));
    item.add(new ContentItem("혀운동", TYPE_CONTENTHEAD));
    item.add(new ContentItem("입술을 조금 벌린 상태에서 혀를 최대한 앞으로 내미는 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "혀 내밀기",R.mipmap.o_s1));
    item.add(new ContentItem(R.raw.newgif09, R.drawable.gifthumb09, TYPE_GIFIMAGE));
    item.add(new ContentItem("혀를 내밀어 윗입술 위까지 최대한 당겨 올려 3초간 유지합니다. 반대로 혀끝을 최대한 밑으로 내려 당겨 3초간 유지합니다. 이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "혀의 상하 운동",R.mipmap.o_s2));
    item.add(new ContentItem(R.raw.newgif10, R.drawable.gifthumb10, TYPE_GIFIMAGE));
    item.add(new ContentItem("혀를 내밀어 오른쪽 입꼬리 끝으로 향하게 하여 3초간 유지합니다. 반대로 혀를 왼쪽 입꼬리 끝으로 향하게 하여 3초간 유지합니다. 이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "혀의 좌우 운동",R.mipmap.o_s3));
    item.add(new ContentItem(R.raw.newgif11, R.drawable.gifthumb11,TYPE_GIFIMAGE));
    item.add(new ContentItem("혀를 내밀어 한쪽 입술 끝에서 시작하여 시계방향으로 천천히 윗입술과 아랫입술을 훑습니다.  다음엔 시계 반대방향으로 똑같이 윗입술과 아랫입술을 훑습니다. 이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "혀로 입술 훑기",R.mipmap.o_s4));
    item.add(new ContentItem(R.raw.newgif12, R.drawable.gifthumb12,TYPE_GIFIMAGE));
    item.add(new ContentItem("입을 다문상태에서 혀로 오른쪽 볼을 힘껏 밀어내고 3초간 유지합니다. 다음엔 반대로 왼쪽 볼을 힘껏 밀어내고 3초간 유지합니다.이 동작을 5회 반복합니다.", TYPE_CONTENTITEM, "혀로 볼 밀어내기",R.mipmap.o_s5));
    item.add(new ContentItem(R.raw.newgif13, R.drawable.gifthumb13,TYPE_GIFIMAGE));
    item.add(new ContentItem("혀 끝으로 윗입술과 아랫입술 안쪽을 강하게 누릅니다. 이 동작을 3회 반복합니다.", TYPE_CONTENTITEM, "혀로 입술 안쪽 눌러주기",R.mipmap.o_s6));
    item.add(new ContentItem(R.raw.newgif14, R.drawable.gifthumb14,TYPE_GIFIMAGE));

    tabItem.add(item);


    item = new ArrayList<>();
    item.add(new ContentItem("3. 침샘 마사지 운동", TYPE_HEAD));
    item.add(new ContentItem(R.drawable.image0a, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "볼근육 자극(볼마사지)",R.mipmap.o_s1));
    item.add(new ContentItem("", TYPE_CONTENTNUM, "양쪽 볼을 손으로 돌려 눌러주면서 마사지해줍니다.",R.mipmap.o_c1));
    item.add(new ContentItem("", TYPE_CONTENTNUM, "양쪽 볼을 손 끝으로 빠르게 두드려줍니다.",R.mipmap.o_c2));
    item.add(new ContentItem("", TYPE_CONTENTNUM, "양쪽 볼을 안에서 바깥쪽으로 밀어줍니다.",R.mipmap.o_c3));

    item.add(new ContentItem(R.drawable.image0b, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "입술근육자극",R.mipmap.o_s2));
    item.add(new ContentItem("", TYPE_CONTENTNUM, "네 손가락으로 양쪽 윗입술과 아랫입술 주위를 힘있게 눌러줘 마사지해줍니다.",R.mipmap.o_c1));
    item.add(new ContentItem("", TYPE_CONTENTNUM, "입꼬리를 올려주어 마사지해줍니다.",R.mipmap.o_c2));
    item.add(new ContentItem("", TYPE_CONTENTNUM, "입술을 모아주어 마사지해줍니다.",R.mipmap.o_c3));

    tabItem.add(item);


    item = new ArrayList<>();
    item.add(new ContentItem("4. 삼킴 운동", TYPE_HEAD));
    item.add(new ContentItem(R.drawable.image0c, TYPE_IMAGE));
    item.add(new ContentItem("노인은 근육이 약해 음식을 삼키는 박자와 꿀떡 삼키는 박자가 맞지 않아 사레가 걸리는 일이 많습니다. 따라서 음식물을 삼킬 때 힘있고 강하게 삼키는 운동이 중요합니다.", TYPE_PRECONTENT));

    tabItem.add(item);
    dbList.add(tabItem);

    item = new ArrayList<>();

    tabItem = new ArrayList<>();
    tabItem.add(item); //0번째 아이템



    item = new ArrayList<>();
    item.add(new ContentItem("1. 옆에 앉아 돕는다", TYPE_HEAD));
    item.add(new ContentItem(R.drawable.image014, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"한쪽 마비나 치매가 있어 혼자서 식사를 하지 못하는 대상자는 도와드립니다.",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"한쪽 마비가 있을 때, 보조자는 마비가 없는 쪽에 앉습니다.",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"옆에 앉아 노인과 같은 시선으로 밥상을 볼 수 있는 장점이 있습니다.",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식사 시 바른자세를 유지합니다.",R.mipmap.g_s4));
    item.add(new ContentItem(R.drawable.image015, TYPE_IMAGE));
    item.add(new ContentItem("돌봄제공자가 서서 식사를 드리면 음식물을 입으로 가져가는 위치가 높아져 노인의 몸이 "
        + "뒤로 젖혀지므로 음식물을 삼키기가 힘들어집니다.", TYPE_CONTENTITEM,"선채로 식사 돕기",R.mipmap.y_s1));
    item.add(new ContentItem("돌봄제공자가 정면에 있으면 편안한 마음으로 식사를 하지 못할 수도 있습니다.", TYPE_CONTENTITEM,"정면에서 식사 돕기",R.mipmap.y_s2));

    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("2. 음식물은 아래에서\n위로 가져간다.", TYPE_HEAD));
    item.add(new ContentItem("식사를 할 때는 상체를 앞으로 살짝 기울여야 합니다.\n얼굴이 위를 향하면 음식물을 삼킬 때 흡인이 될 수 있기 때문입니다.  따라서 대상자의 식사를 도울 때는 반드시 숟가락을 입보다 아래쪽으로 가져가도록 합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem(R.drawable.newimage017, TYPE_NEWIMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "숟가락에 알맞은 양(반쯤 차게)을 올립니다.",R.mipmap.y_s1));
    item.add(new ContentItem(R.drawable.newimage018, TYPE_NEWIMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "숟가락을 아래에서 입 쪽으로 가져가 혀 한가운데에 음식물을 놓습니다.",R.mipmap.y_s2));
    item.add(new ContentItem(R.drawable.image019, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM, "숟가락을 넣은 후 입을 다물게 하고 숟가락을 "
        + "윗입술에 대서 위로 기울여 뺍니다.",R.mipmap.y_s3));

    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("3. 삼킨 것을 확인한다.", TYPE_HEAD));
    item.add(new ContentItem("나이가 들면 음식물을 삼키는 힘이 약해져 식사를 할 때 자주 목이 메게 됩니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("음식물을 잘 삼키는 법", TYPE_CONTENTHEAD));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"음식물을 삼키는 힘이 어느 정도인지 확인합니다.",R.mipmap.y_s1));
    item.add(new ContentItem(R.drawable.newimage020_1, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"보조자의 손끝을 대상자의 목에 가볍게 손을 댑니다.",R.mipmap.y_c1));
    item.add(new ContentItem(R.drawable.newimage020_2, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"대상자의 목에 손을 대고 침을 삼켜보게 합니다.",R.mipmap.y_c2));
    item.add(new ContentItem(R.drawable.newimage020_3, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"침을 꿀꺽 삼킬 때 대상자의 목의 울대뼈가 위로 움직이는지 확인합니다. 위로 움직이면 삼키는 힘이 남아있음을 의미합니다.",R.mipmap.y_c3));

    item.add(new ContentItem("", TYPE_CONTENTITEM,"음식을 잘 삼키지 못하면 대상자를 격려해주세요.",R.mipmap.y_s2));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"침이 없어 식사욕구가 떨어지므로 식사 전에 1~2수저의 물을 주고 식사를 시작하는 것이 효과적입니다.",R.mipmap.y_c1));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"식사 전에 따뜻한 물수건으로 입을 닦아주어 입맛을 돋구는 것도 좋은 방법입니다.",R.mipmap.y_c2));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"대상자의 씹고 삼키는 기능을 확인하고 삼킬 때 기침을 하는지 살펴봅니다.",R.mipmap.y_c3));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"음식을 조리할 때 잘 삼킬 수 있도록 부드럽게 하거나 작게 썰어 준비합니다. 하지만 너무 잘게 만든 음식은 오히려 삼키기 어려울 수 있습니다.",R.mipmap.y_c4));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"서두르지 말고 앉아서 천천히 조금씩 먹도록 합니다. \n예를 들어  “천천히 드세요.”, “서두르지 마세요”등 구체적인 말로 도와줍니다.",R.mipmap.y_c5));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"음식에 따라 온도를 적절히  조절합니다.(찬 음식: 삼키기 쉬움, 따뜻한 음식: 부드러워짐)",R.mipmap.y_c6));
    item.add(new ContentItem(R.drawable.image022_1, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"맑은 국보다는 연하 보조제나 녹말가루를 섞은 걸쭉한 음식이 사레에 덜 걸립니다.",R.mipmap.y_c7));
//    item.add(new ContentItem(R.drawable.image022_2, TYPE_IMAGE));
//    item.add(new ContentItem("", TYPE_CONTENTNUM,"찹쌀떡, 인절미 등은 질식 위험이 있으므로 주지 않습니다.",R.mipmap.y_c8));

    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("4. 질식을 예방한다.", TYPE_HEAD));
    item.add(new ContentItem("노인들은 떡이나 고기 같은 이물질로 인해 기도 폐쇄가 올 위험성이 많습니다. 따라서 찹쌀떡, 인절미 등 질식 위험이 있는 음식은 주지 않습니다.", TYPE_PRECONTENT));
    item.add(new ContentItem(R.drawable.image022_2, TYPE_NEWIMAGE));
    item.add(new ContentItem("증상은 기도폐쇄의 정도에 따라 다르게 나타납니다. 기관지가 부분적으로 폐쇄 됐을 경우에는"
        + " 숨가쁜 증상만 나타날 수 있습니다. 하지만, 심한 폐쇄라면 안절부절, 비정상적 숨소리, 청색증, 의식 저하 등이 일어날 수 "
        + "있습니다. 완전한 폐쇄는 급히 치료하지 않으면 사망하게 될 수도 있습니다.", TYPE_CONTENTITEM, "증상",
        R.mipmap.y_s1));
    item.add(new ContentItem("기도 폐쇄는 부분 기도 폐쇄와 완전 기도 폐쇄로 분류하며 다시 의식이 있느냐 없느냐에 따라서 처치방법은 달라지게 됩니다.", TYPE_CONTENTITEM, "대응 방안",R.mipmap.y_s2));
    item.add(new ContentItem(R.drawable.image036, TYPE_NEWIMAGE));
    item.add(new ContentItem("", TYPE_Heimlich,"하임리히법",R.mipmap.y_c1));
    item.add(new ContentItem(R.mipmap.y_dot,"구조자는 환자의 뒤에서 환자의 허리를 팔로 감쌉니다.", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.y_dot,"한 손은 주먹을 쥐고 엄지를 환자의 배꼽과 검상돌기 중간에 위치하도록 합니다.", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.y_dot,"다른 한 손으로 주먹쥔 손을 감싸 빠르게 위로 4-5회 밀쳐올립니다.", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem("",TYPE_EMPTY));
    item.add(new ContentItem("", TYPE_CONTENTNUM,"의식이 없는 기도 폐쇄일 때",R.mipmap.y_c2));
    item.add(new ContentItem(R.mipmap.y_dot,"환자는 반듯이 눕히고 구조자는 환자이 허벅지에 무릎을 꿇고 앉습니다.", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.y_dot,"손의 가장 밑부분을 환자의 배꼽과 검상돌기 사이에 위치시키고 다른 한 손을 위에 퐤고 4-5회빠르게 밀쳐올립니다.", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.y_dot,"4-5회를 실시한 후 손가락으로 이물질 꺼내기를 합니다.", TYPE_CONTENTSUBITEM));

    tabItem.add(item);

    dbList.add(tabItem);
    item = new ArrayList<>();


    tabItem = new ArrayList<>();
    tabItem.add(item); //0번째 아이템


    item = new ArrayList<>();
    item.add(new ContentItem("1. 기능유지간호란?", TYPE_HEAD));
    item.add(new ContentItem("기능유지간호란 입소 노인의  잔존기능을 유지하기 위해 입소 노인 스스로가 본인의 옷 입기, 식사, 개인위생과 같은 일상활동에 적극적으로 참여하도록 격려하는 것을 말합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem(R.drawable.image023, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"입소노인이 스스로 식사할 수 있도록 식판과 보조장비를 주고 식사를 수행할 충분한 시간을 준다.",R.mipmap.g_s1));
    item.add(new ContentItem(R.drawable.image024, TYPE_IMAGE));
    item.add(new ContentItem("※ 한번에 한가지만 지시한다.\n노인이 수행 후에는 칭찬하고 격려해준다.", TYPE_CONTENTITEM,"스스로 식사할 수 있도록 순서를 알려준다.",R.mipmap.g_s2));
    item.add(new ContentItem(R.drawable.image025, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"손으로 집어먹을 수 있는 음식을 제공하여 스스로 식사하도록 유도합니다.",R.mipmap.g_s3));

    item.add(new ContentItem(R.drawable.image026, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식판은 준비해주었으나 활동을 수행할 시간을 주지 않는다.",R.mipmap.y_s4));
    item.add(new ContentItem(R.drawable.newimage027, TYPE_NEWIMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"음식을 떠먹여준다.",R.mipmap.y_s5));
    item.add(new ContentItem(R.drawable.image028, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"입소노인의 손에 음식을 놓아주거나, 손을 입 쪽으로 움직여 줘보지 않는다.",R.mipmap.y_s6));

    tabItem.add(item);

    dbList.add(tabItem);
    item = new ArrayList<>();

    tabItem = new ArrayList<>();
    tabItem.add(item); //0번째 아이템


    item = new ArrayList<>();
    item.add(new ContentItem("1. 구강간호의 중요성", TYPE_HEAD));
    item.add(new ContentItem("입안이 더러워지면 침이 나오지 못하고 입안이 건조해져 세균이 번식하기 쉽습니다.", TYPE_CONTENTITEM,"침의 분비를 촉진시킵니다.",R.mipmap.y_s1));
    item.add(new ContentItem("나이든 사람들이 많이 걸리는 흡인성 폐렴은 입안에 남아 있던 음식물이나 세균이 폐로 들어가면서 생깁니다.\n"
        + "그러므로 구강을 철저히 관리해 세균 번식을 막으면 폐렴을 예방할 수 있습니다.", TYPE_CONTENTITEM,"폐렴을 예방합니다.",R.mipmap.y_s2));
    item.add(new ContentItem("입안을 청결히 유지하면 치주병이나 충치를 예방할 수 있습니다.\n"
        + "자기 치아로 음식물을 씹어 먹을 수 있으면 영양결핍을 예방할 수 있을 뿐만 아니라 뇌에 적절한 자극을 주어 치매도 예방할 수 있습니다.", TYPE_CONTENTITEM,"치매를 예방합니다.",R.mipmap.y_s3));

    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("2. 구강간호의 순서", TYPE_HEAD));
    item.add(new ContentItem("", TYPE_MOUTH));
    item.add(new ContentItem("준비 ▶ 헹구기 ▶ 틀니세척 ▶ 치아 · 점막 칫솔질 ▶ 혀 닦기 ▶ 헹구기", TYPE_CONTENTITEM,"양치질 순서",R.mipmap.y_s1));
    item.add(new ContentItem(R.drawable.image030, TYPE_IMAGE));

    item.add(new ContentItem("", TYPE_CONTENTITEM,"틀니 세척",R.mipmap.y_s2));
    item.add(new ContentItem("",TYPE_CONTENTNUM,"칫솔로 씻기 : 물을 틀어놓고 칫솔을 이용해 씻습니다. 틀니는 충격에 약해 쉽게 깨지므로 세면대나 대야 등에 물을 받아 놓고 씻습니다.",R.mipmap.y_c1));
    item.add(new ContentItem("",TYPE_CONTENTNUM,"틀니 세정제 : 일반 치약에는 연마제가 들어 있어 틀니에 상처를 낼 수 있으므로 틀니 전용 세정제를 사용하도록 합니다.",R.mipmap.y_c2));
    item.add(new ContentItem("",TYPE_CONTENTNUM,"보관 : 사용하지 않을 때는 변형을 막기 위해 찬물에 담가 둡니다.",R.mipmap.y_c3));

    tabItem.add(item);


    dbList.add(tabItem);
    item = new ArrayList<>();

    tabItem = new ArrayList<>();
    tabItem.add(item); //0번째 아이템


    item = new ArrayList<>();
    item.add(new ContentItem("1. 식사 거부", TYPE_HEAD));
    item.add(new ContentItem("식사를 거부할 때에는 몸의 상태가 나빠지지 않았는 지 살피고 식사 거부의 원인을 파악합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("식사 거부 원인", TYPE_CONTENTHEAD));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"운동부족 및 노화로 인해 에너지 소모량 감소",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"노화로 인한 미각, 후각, 시각능력의 저하",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"신체적 불편감\n입안의 염증, 변비, 배의 통증 등",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"정신적인 불안 및 고독과 상실감",R.mipmap.g_s4));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"기타",R.mipmap.g_s5));
    item.add(new ContentItem(R.mipmap.g_dot,"식욕을 저하시키는 약물", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.g_dot,"음식이라는 것을 이해하지 못하는 경우", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.g_dot,"먹는 방법을 모르는 경우", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.g_dot,"식사를 권하는 태도가 불쾌한 경우", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.g_dot,"급격한 환경변화", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.g_dot,"망상", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem(R.mipmap.g_dot,"틀니문제 등", TYPE_CONTENTSUBITEM));
    item.add(new ContentItem("관리법", TYPE_CONTENTHEAD));

    item.add(new ContentItem("", TYPE_CONTENTITEM,"야단치거나 강제로 먹이거나 식사를 재촉하지 않습니다.",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"거부 시 다른 활동을 하게 하거나 잠시 쉬었다가 식사를 하게 합니다.",R.mipmap.g_s2));
    item.add(new ContentItem(R.drawable.image031, TYPE_IMAGE));

    item.add(new ContentItem(R.drawable.image032, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식사 전에 과도한 활동은 피합니다.",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식사를 권하는 사람에 따라 변할 수 있으므로  다른 사람이나 대상자가 좋아하는 사람이 권해보도록 합니다.",R.mipmap.g_s4));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"거절했던 음식을 장소를 이동해서 다시 권해봅니다.",R.mipmap.g_s5));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"고소한 음식 냄새를 맡게 해 입맛을 돋굽니다. ",R.mipmap.g_s6));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식사를 잘하면 “잘 하셨어요＂라고 칭찬하고 격려해줍니다.",R.mipmap.g_s7));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식사거부가 계속 되면 진료를 의뢰해야 합니다.",R.mipmap.g_s8));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"정기적인 치아검진 및 의치관리에 신경 씁니다.",R.mipmap.g_s9));

    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("2. 이식증", TYPE_HEAD));
    item.add(new ContentItem("치매 노인의 경우 꽃잎, 연필, 종이, 상한 음식 등  먹을 수 없는 것을 먹는 것을 말합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("관리법", TYPE_CONTENTHEAD));

    item.add(new ContentItem("", TYPE_CONTENTITEM,"상한 음식은 대상자 눈에 띄지 않는 곳에 버립니다(쓰레기통, 음식물통도 보이지 않도록  합니다)",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"먹으면 위험한 약품이나 물건은 보이지 않게 치웁니다.",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"대상자를 자주 관찰합니다. 소변이나 대변을 먹을 수도 있으므로 배설물도 철저히 관리합니다.",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"불안을 극복하기 위한 수단으로 먹는 것에 과도하게 집착할 수 있으므로 대상자를 편안하게 해줍니다.",R.mipmap.g_s4));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"심리적 안정을 위해서는 급격한 환경 변화를 줄입니다.",R.mipmap.g_s5));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"공복감이 적으면 이식 행동은 줄어들게 되므로 필요에 따라 간식을 준비합니다.",R.mipmap.g_s6));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"숨기고 있는 음식이나 배설물이 있는지 점검합니다.",R.mipmap.g_s7));


    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("3. 편식", TYPE_HEAD));
    item.add(new ContentItem("편식은 치매대상자에서 흔히 있는 일입니다. 균형 잡힌 식단을 제공하는 것이 무엇보다 중요합니다.\n대상자의 신체상태와 구강에 문제가 있는지 확인하고 필요하면 전문가와 상의하도록 합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("관리법", TYPE_CONTENTHEAD));

    item.add(new ContentItem("", TYPE_CONTENTITEM,"“이거 참 맛있게 생겼네요.”, “몸에 좋은 음식이에요.” 등의 말과 함께 먹지 않는 음식을 부드럽게 권해봅니다.",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"대상자가 좋아하는 음식과 반찬을 준비하고 먹기 편하게 음식을 조리합니다.",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"수저를 사용하기 힘든 대상자는 김밥, 샌드위치 등 손가락으로 집어 먹을 수 있는 음식을 제공합니다.",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"노인은 시야가 좁아지므로 음식이 잘 보이는 위치에 놓아 음식에 관심을 가질 수 있도록 합니다.",R.mipmap.g_s4));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"씹기 힘든 대상자는 음식을 잘게 썰어주거나 빨대컵에 여러 가지 재료로 만든 미음을 담아 먹게 할 수 있습니다.",R.mipmap.g_s5));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"탈수 방지를 위하여 수분을 자주 공급한다.",R.mipmap.g_s6));
    item.add(new ContentItem("주의할 점", TYPE_CONTENTHEAD));
    item.add(new ContentItem(R.drawable.image033, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"돌봄제공자 마음대로 반찬을 섞어 무슨 음식인지 알 수 없게 하는 것은 좋지 않습니다.",R.mipmap.o_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"“뭐하세요. 반찬도 먹어야지요!”라며 야단치거나 강제로 먹이면 안됩니다.",R.mipmap.o_s2));
    tabItem.add(item);

    item = new ArrayList<>();
    item.add(new ContentItem("4. 식사한지 잊음", TYPE_HEAD));
    item.add(new ContentItem("뇌에서 포만감을 느끼는 부위가 손상되거나 기억력이 저하되어  식사한 사실을 잊어버려 발생합니다.", TYPE_PRECONTENT));
    item.add(new ContentItem("관리법", TYPE_CONTENTHEAD));
    item.add(new ContentItem(R.drawable.image034, TYPE_IMAGE));

    item.add(new ContentItem("", TYPE_CONTENTITEM,"정말 배가 고파서 먹을 것을 요구하는 경우는 아닌지 확인합니다.",R.mipmap.g_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"“조금 있다가 좋아하시는 사과 드릴께요.”, “제가 지금 준비하는 중이에요. 곧 만들어 드릴께요.”등과 같이 과식하지 않는 범위에서 대상자의 요구에 응하며 재치있게 반응하도록 합니다.",R.mipmap.g_s2));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"소량씩 여러 번 주는 것이 좋습니다.",R.mipmap.g_s3));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"심리적인 불안감으로 인한 행동인지를 확인합니다.",R.mipmap.g_s4));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"평소 좋아하는 산책이나 다른 놀이 등을 권해보고 함께 해봅니다.",R.mipmap.g_s5));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"식사가 끝나도 식기를 치우지 않는 편이 좋은 경우도 있습니다.",R.mipmap.g_s6));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"종이에 아침, 점심, 저녁이라고 써서, 식사가 끝날 때마다 노인이 표시하도록 하는 방법도 있습니다.",R.mipmap.g_s7));
    item.add(new ContentItem("주의할 점", TYPE_CONTENTHEAD));
    item.add(new ContentItem(R.drawable.image035, TYPE_IMAGE));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"“조금 전에 먹었잖아요!”, “먹은 것도 모르세요?”, “자꾸 조르면 안돼요!”등과 같은 자존심을 상하게 하는 말은 사용하지 않도록 합니다.",R.mipmap.o_s1));
    item.add(new ContentItem("", TYPE_CONTENTITEM,"대상자가 요구하는 대로 음식을 주어서, 과도하게 칼로리를 섭취하지 않도록 합니다.",R.mipmap.o_s2));
    tabItem.add(item);

    dbList.add(tabItem);
  }

  public ArrayList<ArrayList<ArrayList<ContentItem>>> getDbList() {
    return dbList;
  }

  public ArrayList<ContentItem> getItemTextList(int pagenum,int contentid) {
    return dbList.get(pagenum).get(contentid);
  }

  public int getSlide_itemnum() {
    int num = this.slide_itemnum;
    this.slide_itemnum = 1;
    return num;
  }

  public void setSlide_itemnum(int slide_itemnum) {
    this.slide_itemnum = slide_itemnum;
  }

  public int getCurrentPage(int pagetype) {
    return current_page[pagetype];
  }
  public void setCurrentPage(int pagetype,int itemnum) {
    current_page[pagetype] = itemnum;
  }
}
