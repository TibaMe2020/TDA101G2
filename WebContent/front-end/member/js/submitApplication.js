

FilePond.registerPlugin(
  FilePondPluginImagePreview,
  FilePondPluginImageResize,
  FilePondPluginImageTransform,
  FilePondPluginFileValidateType,
  FilePondPluginImageCrop,
  FilePondPluginImageExifOrientation
)

const inputElement = document.querySelector('input[id="document_image"]');
let profile_image_id;
const pond = FilePond.create(inputElement, {
  labelIdle: '上傳圖片',
  allowImagePreview: false,
  acceptedFileTypes: ['image/*'],
  imageResizeTargetWidth: 290,
  imageResizeMode: 'contain',
  maxFiles: 1,
  name: 'document_image',
  onpreparefile: (file, output) => {
    // console.log("this is file id: " + file.id);
    const holder = $('div#document_image_container');
    let url = URL.createObjectURL(output);
    profile_image_id = file.id;
    $(holder).html(`
    <div class="document_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white" id="${file.id}">
      <img src="${url}">
    </div>`);
  },
  onremovefile: (error, file) => {
    // console.log("this is file id: " + file.id);
    const img = $(`div#${file.id}`);
    $(img).remove();
  },
  // files:
});