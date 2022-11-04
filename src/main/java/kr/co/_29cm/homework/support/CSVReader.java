package kr.co._29cm.homework.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import kr.co._29cm.homework.presentation.DataLoader;
import org.springframework.stereotype.Component;

@Component
public class CSVReader implements DataLoader {

    @Override
    public List<String> read(final String path) {
        final List<String> data = new ArrayList<>();
        try (final InputStream in = getClass().getResourceAsStream(path);
             final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            throw new CSVException("csv 파일을 읽던 중 오류가 발생했습니다.");
        }
        return data;
    }
}
