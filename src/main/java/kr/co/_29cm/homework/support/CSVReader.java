package kr.co._29cm.homework.support;

import kr.co._29cm.homework.presentation.DataLoader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class CSVReader implements DataLoader {

    @Override
    public List<String> read(final String path) {
        final List<String> data = new ArrayList<>();

        final ClassPathResource classPathResource = new ClassPathResource("data/items.csv");

        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(classPathResource.getFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new CSVException("csv 파일을 읽던 중 오류가 발생했습니다.");
        }
        return data;
    }
}
