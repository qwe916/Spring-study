package hello.upload.file;
import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;
    public String getFullPath(String filename) {
        return fileDir + filename;
    }
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles)
            throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        //여러 첨부파일들을 반복하면서 저장
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                //storeFileResult에 저장 목록 추가
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException
    {
        //첨부파일이 없으면 null 반환
        if (multipartFile.isEmpty()) {
            return null;
        }
        //파일명
        String originalFilename = multipartFile.getOriginalFilename();
        //저장할 파일명
        String storeFileName = createStoreFileName(originalFilename);
        //파일 저장
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }
    //저장할 파일 명
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);//g확장자
        String uuid = UUID.randomUUID().toString();//uuid 생성
        return uuid + "." + ext;//uuid.확장자
    }
    //확장자를 추출하는 메소드
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
