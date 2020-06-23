<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Service List</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
  <link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet">
  <!-- air datepicker -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/serviceList.css">
</head>

<body>
  <%@ include file="header.jsp"%>



  <div class="container-fluid">
    <div class="row content-height">
      <!-- 側邊欄 結束開始-->
      <div class="col-2">
        <%@ include file="sidebar.jsp"%>
      </div>
      <!-- 側邊欄 結束-->
      <div class="col-9">
        <div class="container-fluid">
          <div class="table-wrapper">
            <div class="table-title">
              <div class="row">
                <div class="col-10 text-left d-flex align-items-center">
                  <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
                    <li class="nav-item">
                      <a class="nav-link active" id="table-title-text" data-toggle="pill" href="#pills-home" role="tab"
                        aria-controls="pills-home" aria-selected="true">店家資料</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link d-none" id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
                        role="tab" aria-controls="pills-profile" aria-selected="false">服務列表
                      </a>
                    </li>
                    <!-- for銜接meber -->
                    <input type="text" id="inputMemberId" style="width: 100px;" value="<%=memberVO.getMember_id()%>" />
                    <!-- for銜接meber -->
                  </ul>
                </div>
                <div class="col-2 d-flex align-items-center justify-content-end">
                  <a href="#add-product" class="d-flex add-product-modal" data-toggle="modal">
                    <i class="fas fa-plus-circle add-product d-none"></i>
                  </a>
                </div>
              </div>
            </div>
            <!-- Modal 確認預約按鈕->跳出視窗 -->
            <div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog"
              aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">狀態</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    ...
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">好的</button>
                  </div>
                </div>
              </div>
            </div>
            <!-- 店家資料 -->
            <div class="tab-content">
              <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                <div class="container input_store">
                  <!-- <form action="" method="post" class="main-form" enctype="multipart/form-data"> -->
                  <div class="row justify-content-around">
                    <div class="col-lg-6 text-left">
                      <div class="form-group">
                        <label for="store_name_update">店家名稱</label>
                        <input type="text" name="name" id="store_name_update" class="form-control" required="required">
                        <div class="invalid-feedback">
                          名稱不可空白
                        </div>
                      </div>
                      <div class="form-group halfdiv halfdiv-1">
                        <label for="store_type">店家類別</label>
                        <select name="store_type" id="store_type_update" class="form-control" required>
                          <option value="">選項...</option>
                          <option value="餐廳">寵物餐廳</option>
                          <option value="旅館">寵物旅館</option>
                          <option value="美容">寵物美容</option>
                          <option value="學校">寵物學校</option>
                          <option value="醫院">寵物醫院</option>
                        </select>
                        <div class="invalid-feedback">
                          此選項必填
                        </div>
                      </div>
                      <div class="form-group halfdiv halfdiv-2">
                        <label for="store_maxcapacity_update">最大可預約人數</label>
                        <input type="number" name="store_maxcapacity" id="store_maxcapacity_update" class="form-control"
                          value='' min='0' max='999' step='1' />
                      </div>

                      <div class="form-group">
                        <label for="store_adress_update">地址</label>
                        <input type="text" name="adress" id="store_adress_update" class="form-control" required />
                        <div class="invalid-feedback">
                          地址不可空白
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="store_phone_update">電話</label>
                        <input type="text" name="phone" id="store_phone_update" class="form-control" required />
                        <div class="invalid-feedback">
                          電話不可空白
                        </div>
                      </div>

                      <div class="form-group">
                        <label for="store_introduction_update">簡介</label>
                        <textarea type="text" name="introduction" id="store_introduction_update" class="form-control"
                          required></textarea>
                      </div>
                    </div>
                    <div class="col-lg-6 text-left">

                      <div class="form-group storeBreak">
                        <div>
                          <label for="storeFirstbreak">公休日1</label>
                          <select size="1" name="storeFirstbreak" class="form-control storeFirstbreak"
                            id="store_firstbreak_update">
                            <option></option>
                            <option value=1>周一</option>
                            <option value=2>週二</option>
                            <option value=3>週三</option>
                            <option value=4>週四</option>
                            <option value=5>週五</option>
                            <option value=6>週六</option>
                            <option value=7>週日</option>
                          </select>
                        </div>
                        <div>
                          <label for="storeSecondbreak">公休日2</label>
                          <select size="1" name="storeSecondbreak" class="form-control storeSecondbreak"
                            id="store_secondbreak_update">
                            <option></option>
                            <option value=1>周一</option>
                            <option value=2>週二</option>
                            <option value=3>週三</option>
                            <option value=4>週四</option>
                            <option value=5>週五</option>
                            <option value=6>週六</option>
                            <option value=7>週日</option>
                          </select>
                        </div>
                      </div>

                      <div class="form-group">
                        <label for="datepicker">其餘公休日</label>
                        <div class="datepicker-here" data-language='zh' id="store_closed_update"></div>
                      </div>
                      <div class="form-group">
                        <label for="store_image">店家圖片</label>
                        <input type="file" id="store_image_update" multiple>
                        <div class="d-flex justify-content-around flex-wrap" id="new-store-image-update"></div>
                      </div>
                    </div>
                  </div>
                  <div class="container-fluid">
                    <div class="row justify-content-center">
                      <div class="col-4">
                        <button type="submit" class="btn btn-primary" id="new-store">新增</button>
                      </div>

                    </div>
                  </div>
                  <!-- </form> -->
                </div>
                <div class="container update_store d-none" id="update_store">
                  <!-- <form action="" method="post" class="main-form" enctype="multipart/form-data"> -->
                  <div class="row justify-content-around">
                    <div class="col-lg-6 text-left">
                      <div class="form-group">
                        <label for="store_name_update">店家名稱</label>
                        <input type="text" name="name" id="store_name_update2" class="form-control" required="required">
                        <div class="invalid-feedback">
                          名稱不可空白
                        </div>
                      </div>
                      <div class="form-group halfdiv halfdiv-1">
                        <label for="store_type">店家類別</label>
                        <select name="store_type" id="store_type_update2" class="form-control" required>
                          <option value="">選項...</option>
                          <option value="餐廳">寵物餐廳</option>
                          <option value="旅館">寵物旅館</option>
                          <option value="美容">寵物美容</option>
                          <option value="學校">寵物學校</option>
                          <option value="醫院">寵物醫院</option>
                        </select>
                        <div class="invalid-feedback">
                          此選項必填
                        </div>
                      </div>
                      <div class="form-group halfdiv halfdiv-2">
                        <label for="store_maxcapacity_update">最大可預約人數</label>
                        <input type="number" name="store_maxcapacity" id="store_maxcapacity_update2"
                          class="form-control" value='' min='0' max='999' step='1' />
                      </div>

                      <div class="form-group">
                        <label for="store_adress_update">地址</label>
                        <input type="text" name="adress" id="store_adress_update2" class="form-control" required />
                        <div class="invalid-feedback">
                          地址不可空白
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="store_phone_update">電話</label>
                        <input type="text" name="phone" id="store_phone_update2" class="form-control" required />
                        <div class="invalid-feedback">
                          電話不可空白
                        </div>
                      </div>

                      <div class="form-group">
                        <label for="store_introduction_update">簡介</label>
                        <textarea type="text" name="introduction" id="store_introduction_update2" class="form-control"
                          required></textarea>
                      </div>
                    </div>
                    <div class="col-lg-6 text-left">

                      <div class="form-group storeBreak2">
                        <div>
                          <label for="storeFirstbreak">公休日1</label>
                          <select size="1" name="storeFirstbreak" class="form-control storeFirstbreak2"
                            id="store_firstbreak_update2">
                            <option></option>
                            <option value=1>周一</option>
                            <option value=2>週二</option>
                            <option value=3>週三</option>
                            <option value=4>週四</option>
                            <option value=5>週五</option>
                            <option value=6>週六</option>
                            <option value=7>週日</option>
                          </select>
                        </div>
                        <div>
                          <label for="storeSecondbreak">公休日2</label>
                          <select size="1" name="storeSecondbreak" class="form-control storeSecondbreak2"
                            id="store_secondbreak_update2">
                            <option></option>
                            <option value=1>周一</option>
                            <option value=2>週二</option>
                            <option value=3>週三</option>
                            <option value=4>週四</option>
                            <option value=5>週五</option>
                            <option value=6>週六</option>
                            <option value=7>週日</option>
                          </select>
                        </div>
                      </div>

                      <div class="form-group">
                        <label for="datepicker">其餘公休日</label>
                        <div class="datepicker-here" data-language='zh' id="store_closed_update2"></div>
                      </div>
                      <div class="form-group">
                        <label for="store_image">店家圖片</label>
                        <input type="file" id="store_image_update2" multiple>
                        <div class="d-flex justify-content-around flex-wrap" id="new-store-image-update2"></div>
                      </div>
                    </div>
                  </div>
                  <div class="container-fluid">
                    <div class="row justify-content-center">
                      <div class="col-4">
                        <button type="submit" class="btn btn-primary" id="update-store">更新</button>
                      </div>

                    </div>
                  </div>
                  <!-- </form> -->
                </div>
              </div>
              <!-- 店家資料 結束 -->
              <!-- 服務列表 -->
              <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">

                <table class="table table-striped table-hover">
                  <thead>
                    <tr>
                      <th>
                        <div class="d-flex justify-content-center">
                          <a href="#" class="d-flex align-items-center">服務名稱<i class="fas fa-sort"></i></a>
                        </div>
                      </th>
                      <th>
                        <div class="d-flex justify-content-center">
                          <a href="#" class="d-flex align-items-center">服務單價<i class="fas fa-sort"></i></a>
                        </div>
                      </th>
                      <th>
                        <div class="d-flex justify-content-center">
                          <a href="#" class="d-flex align-items-center">服務上限<i class="fas fa-sort"></i></a>
                        </div>
                      </th>
                      <th>
                        <div class="d-flex justify-content-center">
                          <a href="#" class="d-flex align-items-center">上/下架<i class="fas fa-sort"></i></a>
                        </div>
                      </th>
                      <th>修改</th>
                      <th>移除</th>
                    </tr>
                  </thead>
                  <tbody id="service-table">
                    <tr>
                      <td>P00001</td>
                      <td>香蕉</td>
                      <td>2020-05-05</td>
                      <td>
                        <div class="d-flex justify-content-center">
                          <label class="switch d-flex align-self-center">
                            <input type="checkbox" name="product_state" checked>
                            <span class="slider round"></span>
                          </label>
                        </div>
                      </td>
                      <td>
                        <div class="d-flex justify-content-center">
                          <a href="#update-product" data-toggle="modal" class="product-update">
                            <i class="fas fa-pen"></i>
                          </a>
                        </div>
                      </td>
                      <td>
                        <a href="#remove-product" data-toggle="modal" class="product-remove">
                          <i class="fas fa-trash"></i>
                        </a>
                      </td>
                    </tr>
                    <tr>
                      <td>P00002</td>
                      <td>芭樂</td>
                      <td>2020-05-05</td>
                      <td>
                        <div class="d-flex justify-content-center">
                          <label class="switch d-flex align-self-center">
                            <input type="checkbox" name="product_state">
                            <span class="slider round"></span>
                          </label>
                        </div>
                      </td>
                      <td class="d-flex justify-content-center">
                        <div class="d-flex justify-content-center">
                          <a href="#update-product" data-toggle="modal" class="product-update">
                            <i class="fas fa-pen"></i>
                          </a>
                        </div>
                      </td>
                      <td>
                        <a href="#remove-product" data-toggle="modal" class="product-remove">
                          <i class="fas fa-trash"></i>
                        </a>
                      </td>
                    </tr>

                  </tbody>
                </table>
              </div>
              <!-- 服務列表 結束 -->
            </div>
          </div>
        </div>
        <!-- Remove Product-->
        <div id="remove-product" class="modal fade">
          <div class="modal-dialog modal-md modal-dialog-centered">
            <div class="modal-content">

              <div class="modal-header d-flex justify-content-center">
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close invisible" data-dismiss="modal" aria-hidden="true"
                    disabled>&times;</button>
                </div>
                <div class="d-flex flex-grow-1 justify-content-center">
                  <h4 class=" modal-title">移除服務</h4>
                </div>
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
              </div>

              <div class="modal-body">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-12">
                      <p class="mb-0">
                        <b><i>確定要移除此項服務?</i></b>
                      </p>
                    </div>
                  </div>
                </div>
              </div>

              <div class="modal-footer border-0">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-6">
                      <button type="button" class="btn btn-danger" id="remove">刪除</button>
                    </div>
                    <div class="col-6">
                      <button type="button" class="btn btn-secondary" id="cancel" data-dismiss="modal">取消</button>
                    </div>
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>

        <!-- Add New Product  -->
        <div id="add-product" class="modal fade">
          <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content mx-auto">

              <div class="modal-header d-flex justify-content-center">
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close invisible" data-dismiss="modal" aria-hidden="true"
                    disabled>&times;</button>
                </div>
                <div class="d-flex flex-grow-1 justify-content-center">
                  <h4 class=" modal-title">新增服務</h4>
                </div>
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
              </div>

              <div class="modal-body">

                <div class="row justify-content-around">

                  <div class="col-lg-8 text-center">
                    <div class="form-group">
                      <label for="service_name">服務項目名稱</label>
                      <input type="text" name="product_version" id="service_name" class="form-control">
                      <div class="invalid-feedback">
                        服務項目名稱不可空白
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="price">單價</label>
                      <input type="number" name="price" id="price" class="form-control">
                      <div class="invalid-feedback">
                        單價不可空白 或 0
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="quantity">數量上限</label>
                      <div>
                        <input type="number" name="quantity" id="quantity" class="form-control">
                        <div class="invalid-feedback">
                          數量上限不可空白 或 0
                        </div>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
              <div class="modal-footer border-0">
                <div class="container-fluid">
                  <div class="row justify-content-center">
                    <div class="col-4">
                      <!-- 正式版 -->
                      <button type="submit" class="btn btn-primary" id="add-service-btn">新增</button>
                      <!-- 測試版 -->
                      <!-- <button type="button" class="btn btn-primary" id="add-product-btn">新增</button> -->
                    </div>
                  </div>
                </div>

              </div>

            </div>
          </div>
        </div>

        <!-- Update Product -->
        <div id="update-product" class="modal fade">
          <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content mx-auto">

              <div class="modal-header d-flex justify-content-center">
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close invisible" data-dismiss="modal" aria-hidden="true"
                    disabled>&times;</button>
                </div>
                <div class="d-flex flex-grow-1 justify-content-center">
                  <h4 class=" modal-title">修改服務</h4>
                </div>
                <div class="d-flex flex-grow-0">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
              </div>

              <div class="modal-body">

                <div class="row justify-content-around">

                  <div class="col-lg-8 text-center">
                    <div class="form-group">
                      <label for="service_name">服務項目名稱</label>
                      <input type="text" name="product_version" id="service_name" class="form-control">
                      <div class="invalid-feedback">
                        服務項目名稱不可空白
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="price">單價</label>
                      <input type="number" name="price" id="price" class="form-control">
                      <div class="invalid-feedback">
                        單價不可空白 或 0
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="quantity">數量上限</label>
                      <div>
                        <input type="number" name="quantity" id="quantity" class="form-control">
                      </div>
                    </div>

                  </div>
                </div>
              </div>
              <div class="modal-footer border-0">
                <div class="container-fluid">
                  <div class="row justify-content-center">
                    <div class="col-4">
                      <!-- 正式版 -->
                      <button type="submit" class="btn btn-primary" id="update-service-btn">修改</button>
                      <!-- 測試版 -->
                      <!-- <button type="button" class="btn btn-primary" id="add-product-btn">新增</button> -->
                    </div>

                  </div>
                </div>

              </div>

            </div>
          </div>
        </div>



      </div>

    </div>
  </div>
  <br>
  <%@ include file="footer.jsp"%>

  <!--   <script src="../../resources/vendors/jquery/jquery.js"></script> -->
  <!--   <script src="../../resources/vendors/popper/popper.min.js"></script> -->
  <!--   <script src="../../resources/vendors/bootstrap/js/bootstrap.min.js"></script> -->
  <script src="https://unpkg.com/filepond-plugin-file-encode/dist/filepond-plugin-file-encode.js"></script>
  <script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
  <script src="https://unpkg.com/filepond-plugin-image-resize/dist/filepond-plugin-image-resize.js"></script>
  <script src="https://unpkg.com/filepond-plugin-image-transform/dist/filepond-plugin-image-transform.js"></script>
  <script src="https://unpkg.com/filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.js">
  </script>
  <script src="https://unpkg.com/filepond/dist/filepond.js"></script>
  <script src="<%=request.getContextPath()%>/resources/vendors/bootstrap/js/bootstrap-input-spinner.js"></script>
  <script src="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/vendors/air_datepicker/datepicker.zh.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/member/js/serviceList.js"></script>

</body>

</html>