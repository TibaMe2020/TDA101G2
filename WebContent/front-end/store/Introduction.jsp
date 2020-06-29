<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <meta http-equiv="Access-Control-Allow-Origin" content="*"> -->
    <title>PETBOX-Introduction</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
        href="<%=request.getContextPath()%>/resources/vendors/datetimepicker/jquery.datetimepicker.css" />
    <link href="<%=request.getContextPath()%>/front-end/store/css/Introduction.css" rel="stylesheet">

</head>

<body>
    <%@ include file="/front-end/member/header.jsp"%>
    <!-- 麵包屑開始 -->
    <nav aria-label="breadcrumb" class="my_breadcrumb">
        <ol class="breadcrumb">
            <img class="bone" src="<%=request.getContextPath()%>/resources/images/Icon awesome-bone.png">
            <li class="breadcrumb-item"><a href="store.jsp">店家</a></li>
            <li class="breadcrumb-item active dropdown" aria-current="page">
                <a href="#" class="dropdown-toggle store-dropdown" data-toggle="dropdown">
                    寵物餐廳
                </a>
                <ul class="dropdown-menu">
                    <li data-store="餐廳"><a href="Introduction.jsp?type=restaurant">寵物餐廳</a></li>
                    <li data-store="旅館"><a href="Introduction.jsp?type=hostel">寵物旅館</a></li>
                    <li data-store="美容"><a href="Introduction.jsp?type=grooming">寵物美容</a></li>
                    <li data-store="學校"><a href="Introduction.jsp?type=school">寵物學校</a></li>
                    <li data-store="診所"><a href="Introduction.jsp?type=hospital">寵物診所</a></li>
                </ul>
            </li>
    </nav>
    <!-- 麵包屑結束 -->

    <!-- 導覽列開始 -->
    <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab"
                aria-controls="pills-home" aria-selected="true">推薦</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab"
                aria-controls="pills-profile" aria-selected="false">介紹</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab"
                aria-controls="pills-contact" aria-selected="false">預約</a>
        </li>
    </ul>
    <!-- 導覽列結束 -->

    <div class="tab-content" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
            <!-- 推薦頁面開始 -->
            <div class="container-fluid">
                <div class="row justify-content-center" id="storelist_card">
                    <!-- 卡片開始 -->
                    <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                        <div class="card" style="width: 18rem;">
                            <img src="../../resources/images/卡片圖.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the
                                    bulk of the card's content.</p>
                                <div class="d-flex justify-content-end">
                                    <a href="#" class="btn btn-primary">reservation</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 卡片結束 -->
                    <!-- 卡片開始 -->
                    <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                        <div class="card" style="width: 18rem;">
                            <img src="../../resources/images/卡片圖.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the
                                    bulk of the card's content.</p>
                                <div class="d-flex justify-content-end">
                                    <a href="#" class="btn btn-primary">reservation</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 卡片結束 -->
                    <!-- 卡片開始 -->
                    <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                        <div class="card" style="width: 18rem;">
                            <img src="../../resources/images/卡片圖.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the
                                    bulk of the card's content.</p>
                                <div class="d-flex justify-content-end">
                                    <a href="#" class="btn btn-primary">reservation</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 卡片結束 -->
                    <!-- 卡片開始 -->
                    <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                        <div class="card" style="width: 18rem;">
                            <img src="../../resources/images/卡片圖.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the
                                    bulk of the card's content.</p>
                                <div class="d-flex justify-content-end">
                                    <a href="#" class="btn btn-primary">reservation</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 卡片結束 -->
                    <!-- 卡片開始 -->
                    <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                        <div class="card" style="width: 18rem;">
                            <img src="../../resources/images/卡片圖.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the
                                    bulk of the card's content.</p>
                                <div class="d-flex justify-content-end">
                                    <a href="#" class="btn btn-primary">reservation</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 卡片結束 -->
                    <!-- 卡片開始 -->
                    <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                        <div class="card" style="width: 18rem;">
                            <img src="../../resources/images/卡片圖.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the
                                    bulk of the card's content.</p>
                                <div class="d-flex justify-content-end">
                                    <a href="#" class="btn btn-primary">reservation</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 卡片結束 -->
                    <!-- 卡片開始 -->
                    <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                        <div class="card" style="width: 18rem;">
                            <img src="../../resources/images/卡片圖.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the
                                    bulk of the card's content.</p>
                                <div class="d-flex justify-content-end">
                                    <a href="#" class="btn btn-primary">reservation</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 卡片結束 -->
                </div>
            </div>
            <!-- 推薦頁面結束 -->
        </div>
        <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
            <!-- 介紹頁面開始 -->
            <!-- 圖片輪播開始 -->
            <div class="row justify-content-center middlerow">
                <div class="col carousel main_img nopadding">
                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="d-block w-100" id="showStore_image1" src="../../resources/images/宣傳主圖1.jpg"
                                    alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" id="showStore_image2" src="../../resources/images/宣傳主圖2.jpg"
                                    alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" id="showStore_image3" src="../../resources/images/宣傳主圖4.jpg"
                                    alt="Third slide">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                            data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                            data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
            <!-- 圖片輪播結束 -->
            <div class="container">
                <div class="row justify-content-center">
                    <div class="row justify-content-center">
                        <div class="col-sm-12 introduction" id="showStore_introduction">
                            從今天起，做一個簡單的人，踏實務實。不沉溺幻想，不庸人自擾。要快樂，要開朗，要堅韌，要溫暖，對人要真誠。要誠懇，要坦然，要慷慨，要寬容，要有平常心。永遠對生活充滿希望，對於困境與磨難，微笑面對。多看書，看好書。少吃點，吃好點。要有夢想，即使遙遠。
                        </div>
                        <div class="col-sm-12 d-flex justify-content-end introduction">
                            <button type="button" class="btn btn-primary booking">預約</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 介紹頁面結束 -->
        </div>
        <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">
            <!-- 預約頁面開始 -->
            <!-- Modal 確認預約按鈕->跳出視窗 -->
            <div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog"
                aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            ...
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal"
                                id="reserve_ok">好的</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Large modal -->
            <div id="linePayModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
                aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        ...
                    </div>
                </div>
            </div>
            <!-- 店家條開始 -->
            <div class="container">
                <div class="row storebox align-items-center">
                    <div class="col-md-3 col-auto" id="stline-name">
                        店家名稱
                    </div>
                    <div class="col-md-7" id="stline-content">店家介紹</div>
                    <div class="col-md-2">
                        <div class="row">
                            <div class="col-md-auto col-6 mx-auto">點擊數</div>
                            <div class="col-md-auto col-6 mx-auto" id="stline-clicks">1,000</div>
                        </div>
                    </div>
                    <!-- <div class="col-md-1 d-flex justify-content-center">
                        <button type="button" class="btn btn-primary">預約</button>
                    </div> -->
                </div>
            </div>
            <!-- 店家條 結束 -->
            <!-- 表格 開始 -->
            <!-- d-none 消失且不占空間 -->
            <!-- 店家預約種類1 -->
            <div class="container reservation d-none" id="storeType1">
                <div class="row">
                    <!-- 表格左半 -->
                    <div class="col-md-6 justify-content-end">
                        <p>
                            <label for="inputName">預約者姓名</label>
                            <input type="text" class="form-control input-name" placeholder="王小明">
                        </p>
                        <div>
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" aria-describedby="emailHelp">
                            <!-- is-valid 通過 is-invalid 未通過 -> 即顯示invalid-feedback -->
                            <div class="invalid-feedback">
                                email格式不正確
                            </div>
                        </div>
                        <div>
                            <label for="inputdate">預約日期 & 時段</label>
                            <input type="text" class="form-control f_date1 input-date">
                            <!-- 大吳老師套件 f_date1 -->
                        </div>
                        <div>
                            <label for="inputPersons">人數</label>
                            <input type='number' class="num-spinner input-persons" value='0' min='0' max='999'
                                step='1'></input>
                        </div>
                    </div>
                    <!-- 表格右半 -->
                    <div class="col-md-6">
                        <div class="row">
                            <form class="form-inline">
                                <div class="form-group mb-2 text_title">
                                    手機號碼
                                </div>
                                <div class="form-group mx-sm-3 mb-2">
                                    <input type="phone-number" class="form-control" placeholder="0912345678">
                                    <!-- is-valid 通過 is-invalid 未通過 -> 即顯示invalid-feedback -->
                                    <div class="invalid-feedback">
                                        手機格式不正確
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary mb-2 d-none">發送驗證碼</button>
                            </form>
                        </div>
                        <div class="row newsletter">
                            <form class="form-inline">
                                <div class="form-group mb-2 text_title d-none">
                                    簡訊驗證碼
                                </div>
                                <div class="form-group mx-sm-3 mb-2 d-none">
                                    <!-- is-invalid 未通過 is-valid 通過 -->
                                    <input type="password" class="form-control" placeholder="123456">
                                    <!-- is-valid 通過 is-invalid 未通過 -> 即顯示invalid-feedback -->
                                    <div class="invalid-feedback">
                                        驗證碼錯誤
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">備註</label>
                            <textarea class="form-control input-note" id="exampleFormControlTextarea1"
                                rows="3"></textarea>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary mb-2 btn_confirm">確認預約</button>
                    </div>
                </div>
            </div>
            <!-- 店家預約種類1 結束 -->
            <!-- 店家預約種類2-1 -->
            <div class="container" id="storeType2">
                <div class="row">
                    <div class="col-md-10">
                        <div class="servicelist">
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                            <p>...</p>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label for="#" class="">合計</label>
                        <input type="text" readonly class="form-control-plaintext bookingTotal" id="" value="$ 0">
                        <button type="submit" class="btn btn-primary nextstep">下一步</button>
                    </div>
                </div>
            </div>
            <!-- 店家預約種類2-1 結束 -->
            <!-- 店家預約種類2-2 -->
            <div class="container reservation" id="storeType2-2">
                <button type="submit" class="btn btn-primary mb-2 usedmemberVO d-none">套用會員資料</button>
                <button type="submit" class="btn btn-primary mb-2 previous-step">上一步</button>
                <div class="row">
                    <!-- 表格1/3 -->
                    <div class="col-md-4 justify-content-end">
                        <p>
                            <label for="inputName">預約者姓名</label>
                            <input type="text" class="form-control input-name" placeholder="王小明">
                        </p>
                        <div>
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" aria-describedby="emailHelp">
                            <!-- is-valid 通過 is-invalid 未通過 -> 即顯示invalid-feedback -->
                            <div class="invalid-feedback">
                                email格式不正確
                            </div>
                        </div>
                        <div>
                            <label for="inputPersons">預約日期</label>
                            <input type="text" class="form-control f_date2 input-date-normal input-date"
                                id="checkinDate">
                            <!-- 大吳老師套件 f_date1 -->
                        </div>
                        <div>
                            <label for="inputPersons">結束日期</label>
                            <input type="text" class="form-control f_date2 input-date-checkout" id="checkoutDate">
                            <!-- 大吳老師套件 f_date1 -->
                        </div>
                        <div>
                            <label for="inputPersons">預約日期 & 時段</label>
                            <input type="text" class="form-control f_date1 input-date-gromming">
                        </div>
                    </div>
                    <!-- 表格2/3 -->
                    <div class="col-md-4">
                        <div class="form-group row total" id="totalonly">
                            <label for="#" class="col-sm-4 col-form-label">合計</label>
                            <div class="col-sm-6">
                                <input type="text" readonly class="form-control-plaintext bookingTotal" value="$ 0">
                            </div>
                        </div>
                        <div id="hostelTotal">
                            <div class="form-group row">
                                <label for="#" class="col-sm-4 col-form-label">單價</label>
                                <div class="col-sm-6">
                                    <input type="text" readonly class="form-control-plaintext bookingTotal" value="$ 0">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="#" class="col-sm-4 col-form-label">天數</label>
                                <div class="col-sm-6">
                                    <input type="text" readonly class="form-control-plaintext dateCount" value="0">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="#" class="col-sm-4 col-form-label">合計</label>
                                <div class="col-sm-6">
                                    <input type="text" readonly class="form-control-plaintext getTotal" value="$ 0"
                                        id="getTotal">
                                </div>
                            </div>
                        </div>
                        <div class="payment_table justify-content-star">
                            <label for="#" class="col-sm-8 col-form-label ">付款方式</label>
                            <div class="form-check d-flex">
                                <div class="col-md-1 d-none d-md-block"></div>
                                <div class="col-md-auto">
                                    <input class="form-check-input" type="radio" name="payment" value="現金"
                                        id="payment1">
                                    <label class="form-check-label" for="defaultCheck1">
                                        現金
                                    </label>
                                </div>
                            </div>
                            <div class="form-check d-flex">
                                <div class="col-md-1 d-none d-md-block"></div>
                                <div class="col-md-auto">
                                    <input class="form-check-input" type="radio" name="payment" value="linepay"
                                        id="payment2">
                                    <label class="form-check-label" for="defaultCheck2">
                                        linepay
                                    </label>
                                    <!-- <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target=".bd-example-modal-lg">Large
                                        modal</button> -->
                                    <a id="linepay" class="d-none">
                                        <img style="width: 100px;"
                                            src="<%=request.getContextPath()%>/resources/images/linepay.png">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 表格3/3 -->
                    <div class="col-md-4">
                        <div class="row">
                            <form class="form-inline">
                                <div class="form-group mb-2 text_title">
                                    手機號碼
                                </div>
                                <div class="form-group mx-sm-3 mb-2">
                                    <input type="phone-number" class="form-control" placeholder="0912345678">
                                    <!-- is-valid 通過 is-invalid 未通過 -> 即顯示invalid-feedback -->
                                    <div class="invalid-feedback">
                                        手機格式不正確
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary mb-2 d-none">發送驗證碼</button>
                            </form>
                        </div>
                        <div class="row newsletter">
                            <form class="form-inline">
                                <div class="form-group mb-2 text_title d-none">
                                    簡訊驗證碼
                                </div>
                                <div class="form-group mx-sm-3 mb-2 d-none">
                                    <!-- is-invalid 未通過 is-valid 通過 -->
                                    <input type="password" class="form-control " placeholder="123456">
                                    <!-- 未通過即顯示invalid-feedback -->
                                    <div class="invalid-feedback">
                                        驗證碼錯誤
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">備註</label>
                            <textarea class="form-control input-note" id="exampleFormControlTextarea1"
                                rows="3"></textarea>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary mb-2 btn_confirm">確認預約</button>
                    </div>
                </div>
            </div>
            <!-- 店家預約種類2-2 結束-->
            <!-- 表格 結束 -->
            <!-- 預約頁面 結束 -->
        </div>
    </div>
    <br>
    <%@ include file="/front-end/member/footer.jsp"%>

    <!--     <script src="../../resources/vendors/jquery/jquery.js"></script> -->
    <!--     <script src="../../resources/vendors/popper/popper.min.js"></script> -->
    <!--     <script src="../../resources/vendors/bootstrap/js/bootstrap.min.js"></script> -->
    <script src="<%=request.getContextPath()%>/resources/vendors/bootstrap/js/bootstrap-input-spinner.js"></script>
    <script src="<%=request.getContextPath()%>/resources/vendors/datetimepicker/jquery.datetimepicker.full.js"></script>
    <!-- <script src="../../resources/vendors/datetimepicker/jquery.js"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/store/js/Introduction.js"></script>
</body>


</html>