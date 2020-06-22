
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
alert(webCtx);
console.log(webCtx);
let projectPath = "http://" + host + webCtx;
console.log(projectPath);

// $(window).on("load", function () {
//   //修改上傳圖片預設文字
//   let label = $('[id*="filepond--drop-label-"]');
//   $(label).html("上傳圖片");
// });

FilePond.registerPlugin(
  FilePondPluginImagePreview,
  FilePondPluginImageResize,
  FilePondPluginImageTransform,
  FilePondPluginFileValidateType,
  FilePondPluginImageCrop,
  FilePondPluginImageExifOrientation
)
const inputElement = document.querySelector('input[id="profile_image"]');
let profile_image_id;
const pond = FilePond.create(inputElement, {
  labelIdle: '上傳圖片',
  allowImagePreview: false,
  acceptedFileTypes: ['image/*'],
  imageResizeTargetWidth: 190,
  imageResizeMode: 'contain',
  maxFiles: 1,
  name: 'profile_image',
  onpreparefile: (file, output) => {
    const holder = $('div#profile_image_container');
    let url = URL.createObjectURL(output);
    console.log(url);
    profile_image_id = file.id;
    $(holder).html(`
    <div class="profile_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white" id="${file.id}">
      <img src="${url}">
    </div>`);
  },
  onremovefile: (error, file) => {
    // console.log("this is file id: " + file.id);
    const img = $(`div#${file.id}`);
    $(img).remove();
  },

  oninit: () => {
    const holder = $('div#profile_image_container');
    $(holder).html(`
    <div class="profile_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded"">
      <img src="hello.world" style="width:190px;">
    </div>`);
  }
});

//${projectPath}/member/profileImage?member_id=MB00001